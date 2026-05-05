package np.com.onlyrj.journalapp.service;

import lombok.extern.slf4j.Slf4j;
import np.com.onlyrj.journalapp.entity.JournalEntry;
import np.com.onlyrj.journalapp.entity.User;
import np.com.onlyrj.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {

    // Constructor Injections of Journal Entry Repository
    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }


    // Service for Create entry
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.saveEntry(user);
        }catch (Exception e){
            log.error("Exception", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    // Get all entries
    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
