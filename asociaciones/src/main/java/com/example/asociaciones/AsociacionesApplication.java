package com.example.asociaciones;

import com.example.asociaciones.entities.*;
import com.example.asociaciones.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication
public class AsociacionesApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(AsociacionesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		manyToManyRemoveBidireccionalFind();
	}
	@Transactional
	public void manyToManyRemoveBidireccionalFind(){

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if(studentOptionalDb.isPresent()){
			System.out.println("peo");
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);
			if(courseOptionalDb.isPresent()){
				System.out.println("peo 2");
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

		}
	}


	@Transactional
	public void manyToManyBidireccionalFind(){

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


	}

	@Transactional
	public void manyToManyBidireccionalRemove(){

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Elba", "Peo");
		Course course1 = new Course("Curso de Java master", "Andres");
		Course course2 = new Course("Curso de Spring", "Andres");

//		student1.setCourses(Set.of(course1, course2));
//		student2.setCourses(Set.of(course2));
		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);
			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

		}

	}

	@Transactional
	public void manyToManyBidireccional(){

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Elba", "Lazo");
		Course course1 = new Course("Curso de Java master", "Andres");
		Course course2 = new Course("Curso de Spring", "Andres");

//		student1.setCourses(Set.of(course1, course2));
//		student2.setCourses(Set.of(course2));
		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);



	}

	@Transactional
	public void manyToManyRemove(){

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Elba", "Lazo");
		Course course1 = new Course("Curso de Java master", "Andres");
		Course course2 = new Course("Curso de Spring", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);
			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

		}

	}

	@Transactional
	public void manyToManyRemoveFind(){

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);
			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);
				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}

		}
	}

	@Transactional
	public void manyToManyFind(){

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToMany(){

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Elba", "Lazo");
		Course course1 = new Course("Curso de Java master", "Andres");
		Course course2 = new Course("Curso de Spring", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}


	@Transactional
	public void oneToOneBidireccionaFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);
			client.setClientDetails(clientDetails);

			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOneBidireccional(){
		Client client = new Client("Erba", "Pura");

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		client.setClientDetails(clientDetails);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById(){

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> optionalClient = clientRepository.findOne(2L); //new Client("Erba", "Pura");
		optionalClient.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne(){

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void manyToOne(){
		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient(){
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

	@Transactional
	public void OneToMany(){
		Client client = new Client("Fran", "Mora");

		Address address1 = new Address("El verjel", 1);
		Address address2 = new Address("Vasco de Gama", 24);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void OneToManyFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("El verjel", 1);
			Address address2 = new Address("Vasco de Gama", 24);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

		});
	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Fran", "Mora");

		Address address1 = new Address("El verjel", 1);
		Address address2 = new Address("Vasco de Gama", 24);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c->{
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void removeAddressFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("El verjel", 1);
			Address address2 = new Address("Vasco de Gama", 24);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			client = clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c->{
				//Address addresAux = c.getAddresses().get(1);
				//c.getAddresses().remove(addresAux);
				//c.getAddresses().remove(1);
				c.getAddresses().remove(1);
				clientRepository.save(c);
				System.out.println(c);
				System.out.println("peo");
			});

		});
	}

	@Transactional
	public void OneToManyBidireccional() {
		Client client = new Client("Fran", "Mora");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

		Set<Invoice> invoices = new HashSet<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);

		invoice1.setClient(client);
		invoice2.setClient(client);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void OneToManyBidireccionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);


			Set<Invoice> invoices = new HashSet<>();
			invoices.add(invoice1);
			invoices.add(invoice2);
			client.setInvoices(invoices);

			invoice1.setClient(client);
			invoice2.setClient(client);

			clientRepository.save(client);

			System.out.println(client);

		});

	}

	@Transactional
	public void removeOneToManyBidireccionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);


			Set<Invoice> invoices = new HashSet<>();
			invoices.add(invoice1);
			invoices.add(invoice2);
			client.setInvoices(invoices);

			invoice1.setClient(client);
			invoice2.setClient(client);

			clientRepository.save(client);

			System.out.println(client);

		});

		Optional<Client> optionalClientBd = clientRepository.findOne(1L);

		optionalClientBd.ifPresent(client -> {
			Invoice invoice3 = new Invoice("Compras de la casa", 5000L);
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			//Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});

		});
	}

}
