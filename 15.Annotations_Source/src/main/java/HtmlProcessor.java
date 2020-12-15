import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.graalvm.compiler.lir.LIRInstruction;


import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlForm", "HtmlInput"})

public class HtmlProcessor extends AbstractProcessor{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //freemarker
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        try {
            configuration.setTemplateLoader(new FileTemplateLoader(new File("src/main/resources")));

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        List<HtmlInputArgs> htmlInputs = new ArrayList<>();
        Map<String, Object> attributes = new HashMap<>();
        // получить типы с аннотаций HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            // получаем полный путь для генерации html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // User.class -> User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                //BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                //test code

                attributes.put("htmlFormAction", annotation.action());
                attributes.put("htmlFormMethod", annotation.method());

                element.getEnclosedElements().stream().forEach(x->{

                    HtmlInput annotationInput = x.getAnnotation(HtmlInput.class);
                    htmlInputs.add(HtmlInputArgs.builder()
                                    .name(annotationInput.name())
                                    .type(annotationInput.type())
                                    .placeholder(annotationInput.placeholder())
                                    .build());
                });
                attributes.put("htmlInput", htmlInputs);

                Template template = configuration.getTemplate("template_for_form.ftlh");
                FileWriter fileWriter = new FileWriter(out.toFile());
                template.process(attributes, fileWriter);
                //--------

//                writer.write("<form action='" + annotation.action() + "' method='" + annotation.method() + "'/>");
//                writer.close();
            } catch (IOException | TemplateException e) {
                throw new IllegalStateException(e);
            }

        }


        return true;
    }
}
