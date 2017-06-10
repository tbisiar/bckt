package com.tbisiar.bckt.service;

import com.tbisiar.bckt.domain.Bucket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tbis163 on 26/03/17.
 */
@Repository
public interface BucketRepo extends MongoRepository<Bucket, Long> {

    List<Bucket> findAll();

}
