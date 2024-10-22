package com.example.springboot_jpa;

import com.example.springboot_jpa.dto.PersonDto;
import com.example.springboot_jpa.entities.Person;
import com.example.springboot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		update();
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween(){
		System.out.println("consultas por rangos");
		List<Person> persons = repository.findAllBetweenId(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("consultas por rangos de nombre");
		persons = repository.findAllBetweenName("J", "Q");
		persons.forEach(System.out::println);

		persons = repository.getAllOrdered();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase(){
		System.out.println("Consulta nombres y apellidos de personas");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("Consulta nombres y apellidos mayuscula");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);
		System.out.println("Consulta nombres y apellidos minuscula");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
		System.out.println("Consulta personalizada persona upper y lower case");
		List<Object[]> personReg = repository.findAllPersonDataListCase();
		personReg.forEach(reg -> System.out.println("id= "+ reg[0] + ", nombre= "+ reg[1] + ", apellido = " + reg[2] + ", lenguaje= " + reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){
		System.out.println("consultas con nombres de personas");
		List<String> names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("consultas con nombres unicos de personas");
		List<String> namesDistinct = repository.findAllNamesDistinct();
		namesDistinct.forEach(System.out::println);

		System.out.println("consultas con lenguajes unicos de personas");
		List<String> languageDistinct = repository.findAllProgrammingLanguageDistinct();
		languageDistinct.forEach(System.out::println);

		System.out.println("consultas con total de lenguajes unicos de personas");
		Long totalLanguageDistinct = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println(totalLanguageDistinct);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries(){

		Scanner scanner = new Scanner(System.in);

		System.out.println("========== consulta solo el nombre por el id ==========");
		System.out.println("Ingrese el id para el nombre");
		Long id = scanner.nextLong();
		String name = repository.getNameById(id);
		System.out.println("Mostrando solo el nombre");
		System.out.println(name);

		Long ID = repository.getIdById(id);
		System.out.println("Mostrando solo el id");
		System.out.println(ID);

		String fullname = repository.getFullNameById(id);
		System.out.println("Mostrando solo el nombre completo");
		System.out.println(fullname);

		Optional<Object> optionalReg = repository.obtenerPersonDataById(id);
		if(optionalReg.isPresent()){
			Object[] personReg = (Object[]) optionalReg.get();
			System.out.println("Mostrando campos personalizados");
			System.out.println("id= "+ personReg[0] + ", nombre= "+ personReg[1] + ", apellido = " + personReg[2] + ", lenguaje= " + personReg[3]);
		}

		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id= "+ reg[0] + ", nombre= "+ reg[1] + ", apellido = " + reg[2] + ", lenguaje= " + reg[3]));

		scanner.close();

	}

	@Transactional(readOnly = true)
	public void personalizedQueries2(){
		System.out.println("Consulta por objeto persona y lenguaje");
		List<Object[]> personsRegs = repository.findAllMixPersonDataList();

		personsRegs.forEach(regs ->{
			System.out.println("programmingLanguage="+regs[1]+", person="+regs[0]);
		});

		System.out.println("consulta que puebla y devuelve objeto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("consulta que puebla y devuelve objeto dto de una clase personalizda");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}

	@Transactional
	public void create(){
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		String lastname = scanner.next();
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);
		//Person person = new Person(null, "Lalo", "Thor", "Python");
		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresar id de la persona a modificar");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
//		optionalPerson.ifPresent(person -> {
//			System.out.println(person);
//			System.out.println("Ingrese el lenguaje de programmacion");
//			String programmingLanguage = scanner.next();
//			person.setProgrammingLanguage(programmingLanguage);
//			Person persondB = repository.save(person);
//			System.out.println(persondB);
//
//		});
//		scanner.close();

		if(optionalPerson.isPresent()){
			Person person = optionalPerson.get();
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programmacion");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);
			Person persondB = repository.save(person);
			System.out.println(persondB);
			scanner.close();
		}
		else {
			System.out.println("El usuario no existe");
		}

	}

	@Transactional
	public void delete(){
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		scanner.close();
		repository.findAll().forEach(System.out::println);
	}

	@Transactional
	public void delete2(){
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(
				person -> repository.delete(person),
				()-> System.out.println("Lo sentimos, no existe la persona con ese id"));
		scanner.close();
		repository.findAll().forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public void findOne(){
//		Person person = null;
//		Optional<Person> optionalPerson = repository.findById(2L);
//		if(optionalPerson.isPresent()){
//			person = optionalPerson.get();
//		}
//		System.out.println(person);
		//repository.findById(1L).ifPresent(person -> System.out.println(person));
		repository.findOne(1L).ifPresent(person -> System.out.println(person));
		repository.findOneLikeName("ia").ifPresent(person -> System.out.println(person));
		repository.findByNameContaining("ia").ifPresent(person -> System.out.println(person));
	}

	@Transactional(readOnly = true)
	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Python", "Pepe");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Python", "Pepe");

		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		List<Object[]> personsvalues = repository.obtenerPersonData();
		personsvalues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);	//
		});
	}

}
