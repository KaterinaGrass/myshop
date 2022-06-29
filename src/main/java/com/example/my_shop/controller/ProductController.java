package com.example.my_shop.controller;

import com.example.my_shop.entity.model.Category;
import com.example.my_shop.entity.model.Product;
import com.example.my_shop.service.CategoryService;
import com.example.my_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




import java.util.List;


@Controller
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    // если карзина в БД

//    @GetMapping("/{id}/cart")
 //  public String addCart (@PathVariable Integer id, Principal principal){
     //   if (principal == null){
          //  return "redirect:/login";
      //  }
      //  productService.addToUserCart(id, principal.getName());
     //  return "redirect:/shop";
 //   }


    @GetMapping(value = "/")
    public String listOfProducts (Model model) {
       List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "products";
        // return findPaginated(1, "title", "asc",model);
    }

    @GetMapping(value = "/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
       List<Category> listCategories = categoryService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        return "products_new";
    }

    @PostMapping(value = "/save")
    public String saveProduct(Product product, Model model)  {

       // productService.createNewProduct(product);
       productService.saveProduct(product);
        //fore image??
       // String filename = URLDecoder.decode(multipartFile.getOriginalFilename(), "UTF-8");

       // String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
      // product.setFilename(filename);

      // Product saveProduct =  productService.createNewProduct(product, multipartFile);
      // String uploadDir = "./product-filenames/"+ saveProduct.getId();
      // Path uploadPath = Paths.get(uploadDir);
      // if (!Files.exists(uploadPath)) {
        //  Files.createDirectory(uploadPath);
      // }

       // try (InputStream inputStream = multipartFile.getInputStream()) {
          //  Path filePath = uploadPath.resolve(filename);
           // System.out.println(filePath.toFile().getAbsolutePath());
           // Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      //  } catch (IOException e) {
          //  throw  new IOException("file not save");
      //  }


        return "redirect:/";
  }


   @GetMapping(value = "/edit/{id}")
    public String showEditProductPage (@PathVariable (name = "id") Integer id, Model model) {
        Product product = productService.findById(id).get();
        model.addAttribute("product", product);
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("listCategories", listCategories);
        return "edit_product";

    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/products")
    public String getAllProducts(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 3) Pageable pageable, Model model) {
        Page<Product> page = productService.findAll(pageable);
        int[] pagination = UtilController.computePagination(page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "products");
        model.addAttribute("page", page);

        return "products";
    }

    @GetMapping("/products/{product}")
    public String editProduct(@PathVariable Product product, Model model) {
        model.addAttribute("product", product);

        return "productEdit";
    }
















}
