package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.repository.SegmentRepository;


@Service
public class SegmentService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SegmentRepository segmentRepo;
	
	public SegmentService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Segment> getAll(int restoranId){
		ArrayList<Segment> segmenti = new ArrayList<>();
		segmentRepo.findByEstablishment_Id(restoranId).forEach(segmenti::add);
		return segmenti;
	}
	
	public Segment getOneById(int restoranId, int id){
		Segment s = segmentRepo.findByEstablishment_IdAndId(restoranId, id);
		return s;
	}
	
	public Segment addNew(Segment segment){
		logger.info("> addNew segment: ", segment);
		return segmentRepo.save(segment);
	}
	
	public Segment editSegment(Segment segment){
		return segmentRepo.save(segment);
	}
	
	public void deleteSegment(int id){
		segmentRepo.delete(id);
	}

}
