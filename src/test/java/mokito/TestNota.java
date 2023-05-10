package mokito;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestNota {

    Service service;
    UI ui;
    NotaXMLRepo notaXMLRepository;
    NotaValidator notaValidator;

    StudentValidator studentValidator;
    StudentXMLRepo studentXMLRepository;

    TemaValidator temaValidator;
    TemaXMLRepo temaXMLRepository;

    @BeforeEach
    void setUp() {
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";
        studentXMLRepository = Mockito.mock(StudentXMLRepo.class);
        temaXMLRepository = Mockito.mock(TemaXMLRepo.class);
        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        notaXMLRepository = Mockito.mock(NotaXMLRepo.class);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    }

    @Test
    void addStudentSuccesfully() {
        Student student = new Student("999", "Ion", 935, "mail@mail.com");
        when(studentXMLRepository.findOne("999")).thenReturn(student);
        service.addStudent(student);
        assertEquals(studentXMLRepository.findOne("999"), student);

        service.deleteStudent("999");
    }

    @Test
    void addTemaSuccesfully() {
        Tema tema = new Tema("111", "descriere", 10, 1);
        when(temaXMLRepository.findOne("111")).thenReturn(tema);
        service.addTema(tema);
        assertEquals(temaXMLRepository.findOne("111"), tema);

        service.deleteTema("111");
    }



    @Test
    void addNotaSuccesfully() {
        Student student = new Student("999", "Ion", 935, "mail@mail.com");
        when(studentXMLRepository.findOne("999")).thenReturn(student);

        service.addStudent(student);
        Tema tema = new Tema("111", "descriere", 10, 1);
        when(temaXMLRepository.findOne("111")).thenReturn(tema);
        service.addTema(tema);

        Nota nota = new Nota("zyz", "999", "111", 10, LocalDate.of(2023, 2, 1));
        when(notaXMLRepository.findOne("zyz")).thenReturn(nota);

        service.addNota(nota, "gg");
        assertEquals(notaXMLRepository.findOne("zyz"), nota);

        service.deleteNota("zyz");
        service.deleteStudent("999");
        service.deleteTema("111");
    }

    @Test
    void allTest(){
        Student student = new Student("999", "Ion", 935, "mail@mail.com");
        when(studentXMLRepository.findOne("999")).thenReturn(student);

        service.addStudent(student);
        assertEquals(studentXMLRepository.findOne("999"), student);

        Tema tema = new Tema("111", "descriere", 10, 1);
        when(temaXMLRepository.findOne("111")).thenReturn(tema);

        service.addTema(tema);
        assertEquals(temaXMLRepository.findOne("111"), tema);


        Nota nota = new Nota("zyz", "999", "111", 10, LocalDate.of(2023, 2, 1));
        when(notaXMLRepository.findOne("zyz")).thenReturn(nota);

        service.addNota(nota, "gg");
        assertEquals(notaXMLRepository.findOne("zyz"), nota);

        service.deleteNota("zyz");

        service.deleteStudent("999");
        service.deleteTema("111");
    }   
}
