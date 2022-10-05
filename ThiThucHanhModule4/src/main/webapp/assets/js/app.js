class App {
    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                position: 'top-end',
                text: t,
                timer: 1500
            })
        }

        static showConfirmDelete() {
            return Swal.fire({
                title: 'Are you sure to deactive the selected customer ?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085D6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, deactive it!',
            });
        }

    }
}


class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class User {
    constructor(id, username, password, fullname, phone, deleted = 0) {
        this.id = id;
        this.username= username;
        this.password = password
        this.fullName = fullname;
        this.phone = phone;
        this.deleted = deleted
    }
}


class Role {
    constructor(id, code) {
        this.id = id;
        this.code = code;
    }
}

class Product {
    constructor(id, urlImage, name, price, quantity, describe,deleted = 0, category) {
        this.id = id;
        this.urlImage = urlImage;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describe = describe;
        this.deleted = deleted
        this.category = category;
    }
}

class Category{
    constructor(id , title) {
        this.id = id;
        this.title = title;
    }
}


class City{
    constructor(id, name, area, population, gdp, description, deleted = 0, country) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.deleted = deleted
        this.country = country;
    }
}


class Country{
    constructor(id, name) {
        this.id = id;
        this.name = name
    }

}

