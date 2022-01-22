package com.mihailobont.zinkworks.controller;


import com.mihailobont.zinkworks.dto.AddAccountRequest;
import com.mihailobont.zinkworks.dto.AddAccountResponse;
import com.mihailobont.zinkworks.dto.AddAtmRequest;
import com.mihailobont.zinkworks.dto.AddAtmResponse;
import com.mihailobont.zinkworks.service.AtmService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/atm")
@AllArgsConstructor
public class AtmController {

    private final AtmService atmService;

    @PostMapping(path = "/add")
    public ResponseEntity<AddAtmResponse> save(@RequestBody AddAtmRequest addAtmRequest){
        return atmService.save(addAtmRequest);
    }
}
