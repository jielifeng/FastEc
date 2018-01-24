package com.example.hasee.festec.exaple.delegate;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.hasee.festec.exaple.ui.camera.CameraImageBean;
import com.example.hasee.festec.exaple.ui.camera.LatteCamera;
import com.example.hasee.festec.exaple.ui.camera.RequestCodes;
import com.example.hasee.festec.exaple.util.callback.CallBackManager;
import com.example.hasee.festec.exaple.util.callback.CallBackType;
import com.example.hasee.festec.exaple.util.callback.IGlobalCallBack;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by hasee on 2017-08-02.
 * 权限检查类，需要权限的时候会根据其是否申请成功而调用相关方法
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDeleagte {

    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera(){
        LatteCamera.start(this);
    }

    //这个是真正调用的方法
    public void startCameraWithCheck(){
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);
        PermissionCheckerDelegatePermissionsDispatcher.checkReadWithCheck(this);
        PermissionCheckerDelegatePermissionsDispatcher.checkWriteWithCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied(){
        Toast.makeText(getContext(),"不允许拍照",Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever(){
        Toast.makeText(getContext(),"永久拒绝拍照",Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request){
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request){
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case RequestCodes.TAKE_PHOTO :
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    //第一个是照片原来的位置，第二个是照片剪裁后存放的位置
                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(400,400)
                            .start(getContext(),this);
                    break;
                case RequestCodes.PICK_PHOTO :
                    if (data != null){
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.creareCropFile().getPath();
                        UCrop.of(pickPath,Uri.parse(pickCropPath))
                                .withMaxResultSize(400,400)
                                .start(getContext(),this);
                    }
                    break;
                case RequestCodes.CROP_PHOTO :
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的图片
                    final IGlobalCallBack callBack = CallBackManager
                            .getInstance()
                            .getCallBack(CallBackType.NO_CROP);
                    if (callBack != null){
                        callBack.executeCallBack(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROE :
                    Toast.makeText(getContext(),"剪裁出错",Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void checkWrite(){
        Toast.makeText(getContext(),"允许写文件",Toast.LENGTH_LONG).show();
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void checkRead(){
        Toast.makeText(getContext(),"允许写文件",Toast.LENGTH_LONG).show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void WriteaDenied(){
        Toast.makeText(getContext(),"不允许写文件",Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteNever(){
        Toast.makeText(getContext(),"永久拒绝写文件",Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteRationale(PermissionRequest request){
        showRationaleDialog(request);
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void onReadDenied(){
        Toast.makeText(getContext(),"不允许读文件",Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    void onReadNever(){
        Toast.makeText(getContext(),"永久拒绝读文件",Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void onReadRationale(PermissionRequest request){
        showRationaleDialog(request);
    }

}
