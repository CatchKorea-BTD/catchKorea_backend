package CatchKorea.backend.service;

import CatchKorea.backend.entity.Hashtag;
import CatchKorea.backend.repositroy.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashtagService {
    private final HashTagRepository hashTagRepository;

    public Optional<Hashtag> findHashtagById(Long id) {
        Optional<Hashtag> hashtag = hashTagRepository.findHashtagById(id);
        return hashtag;
    }
}
