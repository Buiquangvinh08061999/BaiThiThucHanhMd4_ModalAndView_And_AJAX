package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.City;
import com.cg.model.Country;
import com.cg.model.dto.CityDTO;
import com.cg.service.city.ICityService;
import com.cg.service.country.ICountryService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityRestController {

    @Autowired
    private AppUtil appUtils;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ResponseEntity<?> findAllUserDTO(){
        List<CityDTO> findAllUserDTO = cityService.findAllUserDTOByDeletedIsFalse();
        return new ResponseEntity<>(findAllUserDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CityDTO cityDTO, BindingResult bindingResult){
        new CityDTO().validate(cityDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        cityDTO.setId(0l);

        Optional<Country> countryId = countryService.findById(cityDTO.getCountry().getId());
        if(!countryId.isPresent()){
            throw new EmailExistsException("ID country không tồn tại!");
        }

        City createCity = cityService.save(cityDTO.toCity());

        return new ResponseEntity<>(createCity.toCityDTO(), HttpStatus.CREATED);

    }


    //Ham tim kiem theo id, de truyen du lieu vao hien thi Edit
    @GetMapping("/{id}")
    public ResponseEntity<?> getCityById(@PathVariable long id) {

        Optional<City> cityById = cityService.findById(id);
        if (!cityById.isPresent()) {
            throw new ResourceNotFoundException("Invalid user ID");
        }

        return new ResponseEntity<>(cityById.get().toCityDTO(),  HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> doUpdateProduct(@Validated @RequestBody CityDTO cityDTO, BindingResult bindingResult){

        new CityDTO().validate(cityDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }


        Optional<Country> countryId = countryService.findById(cityDTO.getCountry().getId());
        if(!countryId.isPresent()){
            throw new EmailExistsException("ID country không tồn tại!");
        }

        try {
            City updateCity = cityService.save(cityDTO.toCity());

            return new ResponseEntity<>(updateCity.toCityDTO(), HttpStatus.CREATED);

        } catch (DataInputException e) {
            throw new DataInputException("Thông tin tài khoản không hợp lệ ");
        }

    }



    //Hàm xóa mềm theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getAllUserDTO(@PathVariable Long id){

        Optional<City> city = cityService.findById(id);

        if(city.isPresent()){

            city.get().setDeleted(true);

            cityService.save(city.get());

            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }else {
            throw new DataInputException("Thông tin không hợp lệ");
        }


    }
}
