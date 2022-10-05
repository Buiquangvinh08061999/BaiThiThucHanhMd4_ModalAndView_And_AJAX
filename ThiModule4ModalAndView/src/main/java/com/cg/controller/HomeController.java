package com.cg.controller;

import com.cg.model.City;
import com.cg.model.Country;
import com.cg.model.dto.CityDTO;
import com.cg.model.dto.CountryDTO;
import com.cg.service.city.CityService;
import com.cg.service.country.CountryService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;


    /*Học ModalAndView chú ý quan trọng để học*/
    @GetMapping("/list")
    public ModelAndView showPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/layout/list");

        /*Đã lấy ra tất cả các dữ liệu, ta chỉ lấy ra đề hiển thị ở list*/
        List<CityDTO> cityDTOList = cityService.getAllCityDTO();
        modelAndView.addObject("cityDTOList", cityDTOList);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showPageCreate(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/layout/create");

        /*Đã lấy ra tất cả các dữ liệu, ta chỉ lấy ra đề hiển thị ở list, cho người dụng chọn id và name của Country*/
        List<CountryDTO> countryDTOList = countryService.getAllCountryDTO();
        modelAndView.addObject("countryDTOList", countryDTOList);

        modelAndView.addObject("city", new CityDTO());/*đẩy ra cityDTO , mặc định khi tạo dữ liệu mới hoàn toàn, nên ta phải dùng new CityDTO này, tạo đối tượng mới nên phải làm new đối tượng mới*/
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreate(@Validated @ModelAttribute("city") CityDTO cityDTO, BindingResult bindingResult){
        /*Chú ý, mới vào sẽ hiển thị id value Quốc gia và các thông tin, Nhưng khi ta post dữ liệu lên , sẽ bị xóa hết dữ liệu, vì vậy ta phải gọi list lại, và đặt tên đúng modal.addObject ở get, để tái sử dụng ở bên create.html*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/layout/create");

        /*Viết lại để đẩy dữ liệu ra, để khi tạo mới thành công hay thất bại, điều vẽ lại id và tên của nó ở select-option*/
        List<CountryDTO> countryDTOList = countryService.getAllCountryDTO();
        /*Nơi nhận lỗi*/
        if(bindingResult.hasFieldErrors()){
            modelAndView.addObject("hasError", true);

            List<ObjectError> errors = bindingResult.getAllErrors(); /*tạo list nhận hết lỗi*/
            modelAndView.addObject("errors", errors);

            modelAndView.addObject("countryDTOList", countryDTOList);
            modelAndView.addObject("city", new CityDTO());

            return modelAndView;
        }

        /*tìm tất cả các trường theo id của country trong cityDTO để xác thực (option xác nhận true false*/
        Optional<Country> countryDTOOptional = countryService.findById(cityDTO.getCountry().getId());
        if(!countryDTOOptional.isPresent()){
            modelAndView.addObject("errors", "Id Country không tồn tại");

            /*Nhận lỗi xong vẽ lại các value ở ô quốc gia đó*/
            modelAndView.addObject("countryDTOList", countryDTOList);
            modelAndView.addObject("city", new CityDTO());

            return modelAndView; /*nếu lỗi dừng lại luôn, không để chương trình thực thi tiép*/
        }
        /*Kiểm tra nếu tên tồn tại thì báo lỗi dã trùng*/
        Boolean exitsByCityName = cityService.exitsByCityName(cityDTO.getCityName());
        if(exitsByCityName){
            modelAndView.addObject("nameErrors", "Tên thành phố tồn tại vui lòng nhập tên khác");
            /*Nhận lỗi xong vẽ lại các value ở ô quốc gia đó*/
            modelAndView.addObject("countryDTOList", countryDTOList);
            modelAndView.addObject("city", new CityDTO());

            return modelAndView;
        }

        cityDTO.setId(0L);/*làm mới lại dữ liệu*/

        /*Lấy các dữ liệu truyền vào từ cityDTO, set lại cho đối tượng mới City, vì ta trả về City nên phải làm cách này, Còn nếu trên kia khai báo ModelAttribute City city thì ta chỉ cần cityService.save(city) là xong */
        City newCity = new City();
        newCity.setId(cityDTO.getId());
        newCity.setCityName(cityDTO.getCityName());
        newCity.setArea(Long.parseLong(cityDTO.getArea()));
        newCity.setPopulation(Long.parseLong(cityDTO.getPopulation()));
        newCity.setGdp(Long.parseLong(cityDTO.getGdp()));
        newCity.setDescription(cityDTO.getDescription());
        newCity.setCountry(cityDTO.getCountry().toCountry());

        cityService.save(newCity); /*Lưu lại tất cả thêm mới dữ liệu từ form vào đây , từ thằng cityDTO chuyển qua city để đúng với tham số truyền vào của hàm save*/


        /*Nhận lỗi xong vẽ lại các value ở ô quốc gia đó*/
        modelAndView.addObject("countryDTOList", countryDTOList);
        modelAndView.addObject("city", new CityDTO());

        modelAndView.addObject("success", "Tạo mới thành công"); /*thông báo thành công, qua trang list để hứng dữ liệu, hiển thị*/

        return modelAndView;
    }


    /*Bên list đã vẽ lại được id từng thằng, lấy tất cả các trường ra rồi, ta chỉ việc truyền vào đây, để cập nhật lại*/
    @GetMapping("/update/{id}")
    public ModelAndView showPageUpdate(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/layout/update");

        /*Đã lấy ra tất cả các dữ liệu, ta chỉ lấy ra đề hiển thị ở list, cho người dụng chọn id và name của Country*/
        List<CountryDTO> countryDTOList = countryService.getAllCountryDTO();
        modelAndView.addObject("countryDTOList", countryDTOList);

        /*Khi kích vào cập nhật, thì ta lấy các trường theo id truyền vào nó. ta đã có dữ liệu, qua update.html để hiển thị*/
        Optional<CityDTO> cityDTOS = cityService.getAllCityDTOById(id);
        modelAndView.addObject("city", cityDTOS.get());/*vì tìm theo id của cityId, ta đã có bên list, truyền vào optinol để lấy hết dữ liệu các trường ra, để hiển thị bên view update.html*/
        return modelAndView;
    }


    @PostMapping("/update/{id}")
    public ModelAndView doUpdate(@PathVariable Long id,@Validated @ModelAttribute("city") CityDTO cityDTO, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/layout/update");

        /*Phải lấy để hiển thị thông tin value và tên bên phía select cho người dùng lựa chọn*/
        List<CountryDTO> countryDTOList = countryService.getAllCountryDTO();

        /*Nơi nhận lỗi*/
        if(bindingResult.hasFieldErrors()){
            modelAndView.addObject("hasError", true);

            List<ObjectError> errors = bindingResult.getAllErrors(); /*tạo list nhận hết lỗi*/
            modelAndView.addObject("errors", errors);

            modelAndView.addObject("countryDTOList", countryDTOList);
            modelAndView.addObject("city", cityDTO); /*Đã có dự liệu của thằng đấy từ phía getMapping, sổ dữ liệu theo id, giờ ta chỉ xử lí, gọi thằng vào cityDTO*/

            return modelAndView;
        }
        /*tìm tất cả các trường theo id của country trong cityDTO để xác thực (option xác nhận true false*/
        Optional<Country> countryDTOOptional = countryService.findById(cityDTO.getCountry().getId());
        if(!countryDTOOptional.isPresent()){
            modelAndView.addObject("errors", "Id Country không tồn tại");
            /*Nhận lỗi xong vẽ lại các value ở ô quốc gia đó*/
            modelAndView.addObject("countryDTOList", countryDTOList);
            modelAndView.addObject("city", cityDTO);

            return modelAndView; /*nếu lỗi dừng lại luôn, không để chương trình thực thi tiép*/
        }

        Optional<CityDTO> cityDTOOptional = cityService.getAllCityDTOById(id);
        if(!cityDTOOptional.isPresent()){
            modelAndView.addObject("errors", "Id City không tồn tại");
            /*Nhận lỗi xong vẽ lại các value ở ô quốc gia đó*/
            modelAndView.addObject("countryDTOList", countryDTOList);
            modelAndView.addObject("city", cityDTO);
            return modelAndView;
        }
        cityDTO.setId(0L);
        /*Cập nhật lại dữ liệu lấy được theo id của thằng đấy*/

        City updateCity = new City();
        updateCity.setId(id);
        updateCity.setCityName(cityDTO.getCityName());
        updateCity.setPopulation(Long.parseLong(cityDTO.getPopulation()));
        updateCity.setGdp(Long.parseLong(cityDTO.getGdp()));
        updateCity.setArea(Long.parseLong(cityDTO.getArea()));
        updateCity.setDescription(cityDTO.getDescription());
        updateCity.setCountry(cityDTO.getCountry().toCountry());

        cityService.save(updateCity); /*save lại thông tin, Thằng Create và Update, mục đích cuối của nó chỉ để lưu lại dữ liệu từ form truyền lên, Nên 2 thằng đấy sử dụng cùng chung hàm Save*/

        /*Thành công xong vẽ lại các value ở ô quốc gia đó*/
        modelAndView.addObject("countryDTOList", countryDTOList);
        modelAndView.addObject("city", updateCity);

        modelAndView.addObject("success", "Cập nhật thành công"); /*thông báo thành công, qua trang list để hứng dữ liệu, hiển thị*/

        return modelAndView;
    }
    /*Truyền id đã tìm được list vào*/
    /*Show thông tin, trả về optinol tất cả dữ liệu của id đấy ra, qua bên info để sử dụng lại*/
    @GetMapping("/info/{id}")
    public ModelAndView doInfoById(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("layout/info");

        List<CountryDTO> countryDTOList = countryService.getAllCountryDTO();
        modelAndView.addObject("countryDTOList", countryDTOList);

        /*Lấy được tất cả dữ liệu theo id truyền vào, sổ ra tất cả các trường theo id*/
        Optional<CityDTO> cityDTOOptional = cityService.getAllCityDTOById(id);
        modelAndView.addObject("city" ,cityDTOOptional.get());

        return modelAndView;
    }


    /*Phải bỏ kiểu String mới return kiểu đấy được*/
    @GetMapping("/delete/{id}")
    public String doDeleteById(@PathVariable Long id){

        cityService.deleteById(id); /*Xóa thành công thì, thoát khỏi trang đấy, chuyển hướng đến trang list danh sách ra*/

        return "redirect:/home/list";
    }








}
