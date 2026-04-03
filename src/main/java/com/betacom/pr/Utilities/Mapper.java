package com.betacom.pr.Utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.pr.dto.outputs.CategoryDTO;
import com.betacom.pr.dto.outputs.ImageDTO;
import com.betacom.pr.dto.outputs.ProductDTO;
import com.betacom.pr.dto.outputs.SubcategoryDTO;

import com.betacom.pr.models.Category;
import com.betacom.pr.models.Image;
import com.betacom.pr.models.Product;
import com.betacom.pr.models.Subcategory;

public class Mapper {

	
	public static CategoryDTO buildCategoryDTO(Category c) {
		if (c == null) return null;
		return CategoryDTO.builder()
				.id(c.getId())
				.name(c.getName())
				.build();
	}

	public static List<CategoryDTO> buildCategoryDTO(List<Category> lc) {
		return lc.stream()
				.map(Mapper::buildCategoryDTO)
				.collect(Collectors.toList());
	}

	public static SubcategoryDTO buildSubcategoryDTO(Subcategory s) {
		if (s == null) return null;
		return SubcategoryDTO.builder()
				.id(s.getId())
				.subcategoryName(s.getName())
				.categoryId((s.getCategory() == null) ? null : s.getCategory().getId())
				.build();
	}

	public static List<SubcategoryDTO> buildSubcategoryDTO(List<Subcategory> ls) {
		return ls.stream()
				.map(Mapper::buildSubcategoryDTO)
				.collect(Collectors.toList());
	}

	public static ImageDTO buildImageDTO(Image i) {
		if (i == null) return null;
		return ImageDTO.builder()
				.imageId(i.getId())
				.link(i.getLink()) 
				.productId((i.getProduct() == null) ? null : i.getProduct().getId())
				.build();
	}

	public static List<ImageDTO> buildImageDTO(List<Image> li) {
		return li.stream()
				.map(Mapper::buildImageDTO)
				.collect(Collectors.toList());
	}

	public static ProductDTO buildProductDTO(Product p) {
	    if (p == null) return null;	    
	    return ProductDTO.builder()
	            .id(p.getId())
	            .name(p.getName())
	            .description(p.getDescription())
	            .price(p.getPrice())
	            .stock(p.getStock())
	            .isDeleted(p.getIsDeleted())
	            .subcategoryId((p.getSubcategory() == null) ? null : p.getSubcategory().getId())
	            .subcategoryName((p.getSubcategory() == null) ? null : p.getSubcategory().getName())
	            .images(p.getImages() == null ? null : p.getImages().stream()
	                    .map(img -> ImageDTO.builder()
	                            .imageId(img.getId())
	                            .link(img.getLink())
	                            .productId(p.getId())
	                            .build())
	                    .toList())
	            .build();
	}

	public static List<ProductDTO> buildProductDTO(List<Product> lp) {
		return lp.stream()
				.map(Mapper::buildProductDTO)
				.collect(Collectors.toList());
	}
}