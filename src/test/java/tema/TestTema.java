package tema;

import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;
import view.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTema {

    Service service;
    StudentValidator studentValidator;
    StudentXMLRepo studentXMLRepository;
    UI ui;

    @BeforeEach
    void setUp() {
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
    void addTemaIdEmpty() {
        Tema tema = new Tema("", "descriere", 10, 2);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaIdNull() {
        Tema tema = new Tema(null, "descriere", 10, 2);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaDescriereEmpty() {
        Tema tema = new Tema("", "", 10, 2);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaSmallDeadline()
    {
        Tema tema = new Tema("100", "asd", 0, 2);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaSmallBigDeadline()
    {
        Tema tema = new Tema("100", "asd", 16, 2);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaSmallPrimire()
    {
        Tema tema = new Tema("100", "asd", 2, 0);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaBigPrimire()
    {
        Tema tema = new Tema("100", "asd", 2, 16);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }
}
