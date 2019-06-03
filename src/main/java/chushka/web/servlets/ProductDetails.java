package chushka.web.servlets;

import chushka.domain.enteties.Product;
import chushka.repository.ProductRepository;
import chushka.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetails extends HttpServlet {

    private final static String HTML_PRODUCT_DETAILS_FILE_PATH = "E:\\Java\\JavaWeb\\HomeWork\\Chushka\\src\\main\\resources\\viwes\\product-details.html";
    private final HtmlReader htmlReader;
    private final ProductRepository productRepository;

    @Inject
    public ProductDetails(HtmlReader htmlReader, ProductRepository productRepository) {
        this.htmlReader = htmlReader;
        this.productRepository = productRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Product product = this.productRepository.findByName(name);

        String htmlDetailsContent = this.htmlReader.readHtmlFile(HTML_PRODUCT_DETAILS_FILE_PATH)
                .replace("{{description}}", product.getDescription())
                .replace("{{type}}", product.getType().name());

        resp.getWriter().println(htmlDetailsContent);
    }
}
