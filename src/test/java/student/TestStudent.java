package student;

import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentFileRepository;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;
import view.UI;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {

    Service service;
    StudentValidator studentValidator;
    StudentXMLRepo studentXMLRepository;
    UI ui;

    @BeforeEach
    void setUp(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    }

    @Test
    void addStudentSuccesfully(){
        Student student = new Student("999", "Ion", 935, "mail@mail.com");

        service.addStudent(student);
        assertEquals(studentXMLRepository.findOne("999"), student);

        service.deleteStudent("999");
    }

    @Test
    void addStudentIdEmpty(){
        Student student = new Student("", "Ion", 935, "mail@mail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

}
