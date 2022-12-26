package com.esprit.examen.services;




import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class SecteurServiceImplTest {
    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    SecteurActiviteServiceImpl secteurActiviteServiceImpl;

    SecteurActivite secteurActivite = new SecteurActivite(1L, "df", "f1", null);
    List<SecteurActivite> listSecteurActivite = new ArrayList<SecteurActivite>() {
        {
            add(new SecteurActivite(2L, "s", "f2", null));
            add(new SecteurActivite(3L, "b", "f3", null));
        }
    };
    @Test
    void testRetrieveAllSecteurActivites() {
        List<SecteurActivite> actualRetrieveAllSecteurActivitesResult = this.secteurActiviteServiceImpl
                .retrieveAllSecteurActivite();
        Assertions.assertEquals(0, actualRetrieveAllSecteurActivitesResult.size());
        verify(secteurActiviteRepository).findAll();

    }
    @Test
    public void testRetrieveSectuerActivite() {
        when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(secteurActivite));
        SecteurActivite secteurActivite1 = secteurActiviteServiceImpl.retrieveSecteurActivite(Long.valueOf(1));
        assertNotNull(secteurActivite1);
        verify(secteurActiviteRepository).findById((Long) any());

    }

    @Test
    void testAddSecteurActivite() {
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(10L);
        secteurActivite.setCodeSecteurActivite("op1");
        secteurActivite.setLibelleSecteurActivite("123");
        secteurActivite.setFournisseurs(null);
        when(secteurActiviteRepository.save(any())).thenReturn(secteurActivite);
        assertSame(secteurActivite, secteurActiviteServiceImpl.addSecteurActivite(secteurActivite));
        assertNotNull(secteurActivite.getIdSecteurActivite());
        verify(secteurActiviteRepository).save(any());
    }

    

    @Test
    void testDeleteSecteurActivite() {
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(10L);
        secteurActivite.setCodeSecteurActivite("op1");
        secteurActivite.setLibelleSecteurActivite("123");
        secteurActivite.setFournisseurs(null);
        doNothing().when(secteurActiviteRepository).deleteById((Long) any());
        secteurActiviteServiceImpl.deleteSecteurActivite(secteurActivite.getIdSecteurActivite());
        verify(secteurActiviteRepository).deleteById((Long) any());
    }

}