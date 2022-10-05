package com.cg.model.dto;

import com.cg.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter

@NoArgsConstructor
@Accessors(chain = true)
public class CountryDTO {


    private Long id;

    private String nameCountry;

    /*Viết ra để repo sử dụng, tránh khó hiểu code khi đọc vào sẽ k tìm thấy contructor này*/
    public CountryDTO(Long id, String nameCountry) {
        this.id = id;
        this.nameCountry = nameCountry;
    }

    public Country toCountry(){
        return new Country()
                .setId(id)
                .setNameCountry(nameCountry);
    }
}
