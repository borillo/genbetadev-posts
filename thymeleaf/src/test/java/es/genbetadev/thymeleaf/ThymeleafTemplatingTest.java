package es.genbetadev.thymeleaf;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class ThymeleafTemplatingTest
{
    private TemplateEngine templateEngine;

    public ThymeleafTemplatingTest()
    {
        templateEngine = TemplateEngineFactory.getTemplateEngine();
    }

    @Test
    public void simple()
    {
        IContext context = new Context();
        context.getVariables().put("titulo", "Mi título en la plantilla");

        String result = templateEngine.process("simple", context);

        assertTrue(result.contains("<h1>Mi título en la plantilla</h1>"));
    }

    @Test
    public void bean()
    {
        Dato dato = new Dato();
        dato.setTitulo("Mi título en la plantilla");

        IContext context = new Context();
        context.getVariables().put("dato", dato);

        String result = templateEngine.process("bean", context);

        assertTrue(result.contains("<h1>Mi título en la plantilla</h1>"));
    }

    @Test
    public void simpleMultilang()
    {
        String result = templateEngine.process("simple_multilang", new Context(new Locale("en")));
        assertTrue(result.contains("<h1>Wellcome to Genbeta Dev web!!</h1>"));

        result = templateEngine.process("simple_multilang", new Context());
        assertTrue(result.contains("<h1>Bienvenido a la web de Genbeta Dev!!</h1>"));
    }

    @Test
    public void collection()
    {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Producto 1");

        IContext context = new Context();
        context.getVariables().put("productos", Collections.singletonList(producto));

        String result = templateEngine.process("colecciones", context);
        System.out.println(result);
        assertTrue(result.contains("<td>Producto 1</td>"));
    }

}