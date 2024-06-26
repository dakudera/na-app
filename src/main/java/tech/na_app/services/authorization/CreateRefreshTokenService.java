package tech.na_app.services.authorization;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.na_app.entity.auth.RefreshToken;
import tech.na_app.entity.auth.RefreshTokenSequence;
import tech.na_app.model.ApiException;
import tech.na_app.repository.RefreshTokenRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateRefreshTokenService {

    @Value("${security.jwt.refresh-expiration}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public String createRefreshToken(ObjectId userId) {
        RefreshToken refreshToken = new RefreshToken();

        RefreshTokenSequence sequenceNumber = (RefreshTokenSequence) sequenceGeneratorService
                .getSequenceNumber(RefreshToken.SEQUENCE_NAME, RefreshTokenSequence.class);
//        refreshToken.setId(sequenceNumber.getSeq());
        refreshToken.setUserId(userId);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws ApiException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new ApiException(500, "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }
}