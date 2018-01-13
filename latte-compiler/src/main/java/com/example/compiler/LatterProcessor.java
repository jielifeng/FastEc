package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by hasee on 2017-10-23.
 */
@SuppressWarnings("unused")
@AutoService(Processor.class)
public final class LatterProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportedAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation :supportedAnnotations){
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations(){
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateAppRegisterCoder(roundEnvironment);
        generateEntryCoder(roundEnvironment);
        generatePayEntryCoder(roundEnvironment);
        return false;
    }

    private void scan(RoundEnvironment environment,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor){

        for (Element typeElement : environment.getElementsAnnotatedWith(annotation)){
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            System.out.println("不存在的不存在的不存在的不存在的     typeElement.getKind()"+typeElement.getKind());
            System.out.println("不存在的不存在的不存在的不存在的     typeElement.asType().toString()"+typeElement.asType().toString());
            System.out.println("不存在的不存在的不存在的不存在的     typeElement.asType()"+typeElement.asType().getKind());
            for (AnnotationMirror annotationMirror : annotationMirrors){
                final Map<? extends ExecutableElement,? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry :
                        elementValues.entrySet()){
                    System.out.println("bbbbbbbbbbb     entry.getValue().toString()"+entry.getValue().toString());
                    System.out.println("bbbbbbbbbbb     entry.getValue().getValue()"+entry.getValue().getValue());
                    System.out.println("bbbbbbbbbbb     entry.getValue().getClass()"+entry.getValue().getClass());
                    entry.getValue().accept(visitor,null);
                }
            }
        }

    }

    private void generateEntryCoder(RoundEnvironment environment){
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(environment,EntryGenerator.class,entryVisitor);
    }

    private void generatePayEntryCoder(RoundEnvironment environment){
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setFiler(processingEnv.getFiler());
        scan(environment,EntryGenerator.class,payEntryVisitor);
    }

    private void generateAppRegisterCoder(RoundEnvironment environment){
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor();
        appRegisterVisitor.setFiler(processingEnv.getFiler());
        scan(environment,EntryGenerator.class,appRegisterVisitor);
    }
}
