package com.example.obspringsesion456;

import com.example.obspringsesion456.entities.Laptop;
import com.example.obspringsesion456.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringSesion456Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObSpringSesion456Application.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);
		System.out.println("Cantidad de Laptops almacenadas en BD: " + repository.count());
		Laptop laptop1 = new Laptop(null, "Lenovo", "Phantom Black", "Intel Core i5", 15.6, 128, 8, 230999.00, true);
		Laptop laptop2 = new Laptop(null, "Acer", "A315-34", "Intel Celeron", 15.6, 500, 4, 99000.00, true);
		repository.save(laptop1);
		repository.save(laptop2);
		System.out.println("Cantidad de Laptops almacenadas en BD: " + repository.count());

	}

}
