package chushka.web.servlets;

import chushka.repository.ProductRepository;
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
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final static String HTML_INDEX_FILE_PATH = "E:\\Java\\JavaWeb\\HomeWork\\Chushka\\src\\main\\resources\\viwes\\index.html";
    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper, ProductRepository productRepository) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.readHtmlFile(HTML_INDEX_FILE_PATH)
                .replace("{{listItem}}", listItems());

        PrintWriter writer = resp.getWriter();

        writer.println(htmlFileContent);
    }

    private String listItems(){
        StringBuilder builder = new StringBuilder();

        productRepository.findAll().forEach(item ->
                builder.append(String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>", item.getName(), item.getName())));

        return builder.toString().trim();
    }
}
