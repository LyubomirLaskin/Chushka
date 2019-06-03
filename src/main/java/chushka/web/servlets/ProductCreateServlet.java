package chushka.web.servlets;

import chushka.domain.enteties.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.util.HtmlReader;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {

    private final static String HTML_CREATE_PRODUCT_FILE_PATH = "E:\\Java\\JavaWeb\\HomeWork\\Chushka\\src\\main\\resources\\viwes\\create-product.html";
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Inject
    public ProductCreateServlet(HtmlReader htmlReader, ModelMapper modelMapper, ProductService productService) {
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String htmlContent = this.htmlReader.readHtmlFile("E:\\Java\\JavaWeb\\HomeWork\\Chushka\\src\\main\\resources\\viwes\\create-product.html")
                .replace("{{typeOptions}}",typeOptions());

        resp.getWriter().println(htmlContent);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();

        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));

        this.productService.saveProduct(productServiceModel);

        resp.sendRedirect("/");
    }

    private String typeOptions(){

        StringBuilder builder = new StringBuilder();

        Arrays.stream(Type.values()).forEach(el -> builder
                .append(String.format("<option value=\"%s\">%s</option>",el.name(),el.name()))
                .append(System.lineSeparator()));

        return builder.toString().trim();
    }
}
