package com.petclinic.owner;

import com.petclinic.owner.controllers.OwnerController;
import com.petclinic.owner.models.Owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class PetClinicOwner {

	private static ConfigurableApplicationContext context;
	private static OwnerController ownerController;

	public static void main(String[] args) {

		context = SpringApplication.run(PetClinicOwner.class, args);
		generateData();
	}

	private static void generateData(){
		// Need a reference to the OwnerController to run our tests.
		// We use the context to retrieve managed beans by name.
		// The name of the bean is the type of bean (it's name) in camelcase, with the first letter lowercase (by default).
		ownerController = (OwnerController) context.getBean("ownerController");

		// Create Owners
		Owner owner1 = Owner.builder().withName("Homer Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
		Owner owner2 = Owner.builder().withName("Marge Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
		Owner owner3 = Owner.builder().withName("Bart Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
		Owner owner4 = Owner.builder().withName("Lisa Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();

		// Add Owners
		ownerController.add(owner1);
		ownerController.add(owner2);
		ownerController.add(owner3);
		ownerController.add(owner4);

		List<Owner> owners = ownerController.getAll();

		owners.forEach(owner -> {
			System.out.println(owner.toString());
		});
	}

}
