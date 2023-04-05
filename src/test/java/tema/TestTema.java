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
    void addTemaEmptyId()
    {
        Tema tema = new Tema("", "asd", 12, 12);

        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaNullId()
    {
        Tema tema = new Tema(null, "asd", 12, 12);

        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @Test
    void addTemaEmptyDescription()
    {
        Tema tema = new Tema("100", "", 12, 12);

        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }
}
