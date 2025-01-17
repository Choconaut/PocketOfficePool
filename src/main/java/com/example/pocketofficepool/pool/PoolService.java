package com.example.pocketofficepool.pool;

import com.example.pocketofficepool.gametype.GameType;
import com.example.pocketofficepool.gametype.GameTypeRepository;
import com.example.pocketofficepool.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional
public class PoolService {

    private final PoolRepository poolRepository;

    public PoolService(PoolRepository poolRepository) {
        this.poolRepository = poolRepository;
    }

    public Pool save(Pool pool) {
        return this.poolRepository.save(pool);
    }

    public Pool findById(UUID poolId) {
        return this.poolRepository.findById(poolId)
                .orElseThrow(() -> new ObjectNotFoundException("pool", poolId));
    }

    public Pool update(UUID poolId, Pool update) {
        Pool pool = this.findById(poolId);

        pool.setBettingType(update.getBettingType());
        pool.setCloseTime(update.getCloseTime());
        pool.setOpenTime(update.getOpenTime());
        pool.setName(update.getName());
        pool.setStatus(update.getStatus());

        return this.save(pool);
    }

    public void delete(UUID poolId) {
        Pool pool = this.findById(poolId);

        pool.setDeletedAt(ZonedDateTime.now());
        this.poolRepository.save(pool);
    }
}
