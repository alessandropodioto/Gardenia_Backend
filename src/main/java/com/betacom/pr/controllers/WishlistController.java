package com.betacom.pr.controllers;

import com.betacom.pr.dto.inputs.WishlistReq;
import com.betacom.pr.dto.outputs.WishlistDTO;
import com.betacom.pr.services.interfaces.IWishlistServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/wishlist")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

    private final IWishlistServices wishlistS;

    @PostMapping("/add")
    public void add(@RequestBody WishlistReq req) throws Exception {
        wishlistS.add(req);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable Integer id) throws Exception {
        wishlistS.remove(id);
    }

    @GetMapping("/list/{userName}")
    public List<WishlistDTO> getByUser(@PathVariable String userName) {
        return wishlistS.getByUser(userName);
    }
}