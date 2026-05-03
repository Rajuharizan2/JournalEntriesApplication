package np.com.onlyrj.journalapp.repository;

import np.com.onlyrj.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName (String username);
}
