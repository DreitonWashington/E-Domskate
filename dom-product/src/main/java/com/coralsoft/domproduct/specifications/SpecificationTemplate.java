package com.coralsoft.domproduct.specifications;

import com.coralsoft.domproduct.models.ProductModel;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    @Or({
            @Spec(path = "name", spec = LikeIgnoreCase.class),
            @Spec(path = "brand.name", params = "brandName", spec = LikeIgnoreCase.class)
    })
    public interface ProductSpec extends Specification<ProductModel> {}
}
