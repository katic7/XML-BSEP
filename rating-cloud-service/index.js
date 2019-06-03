const connection = require('./database')

import('@google/cloud-debug');
exports.newRating = function newRating(req, res) {
    let userID = req.body.userID;
    let comment = req.body.comment;
    let rating = req.body.rating;
    let accommodationID = req.body.accommodationID;
    connection.query("insert into ratings (userID, comment, rating, accommodationID, published) values (?, ?, ?, ?, ?)", [userID, comment, rating, accommodationID, 0], (err, result) => {
	if (err) res.status(400).send(err);
	else res.status(200).send('upisano '+ comment);
    });
};



import('@google/cloud-debug');
exports.getAllRatings = function getAllRatings(req, res) {
    connection.query("select * from ratings", (err, result)=> {
	if (err) res.status(400).send('nije dobro getovanje svih');
	else res.status(200).send(result);
    });
};

import('@google/cloud-debug');
exports.getSpecificRating = function getSpecificRating(req, res) {
    connection.query("select id, userID, comment, rating, accommodationID, published from ratings where id="+req.query.id,
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result[0]);
	    }
	});
};

import('@google/cloud-debug');
exports.getRatingsFromSpecificAcc = function getRatingsFromSpecificAcc(req, res) {
    connection.query("select * from ratings where accommodationID="+req.query.accommodationID,
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result);
	    }
	});
};

import('@google/cloud-debug');
exports.getRatingScore = function getRatingScore(req, res) {
    connection.query("select avg(rating) as ratingScore, accommodationID  from ratings where accommodationID="+req.query.accommodationID,
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result);
	    }
	});
};

import('@google/cloud-debug');
exports.publishComment = function publishComment(req, res) {
    let flag = req.body.flag;
    let id = req.body.id;
    
    connection.query("UPDATE `ratings` SET `published` = "+flag+" where id = "+id,
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result);
	    }
	});
};

import('@google/cloud-debug');
exports.getUnpublishedComments = function getUnpublishedComments(req, res) {
    connection.query("select * from ratings where published=false",
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result);
	    }
	});
};


import('@google/cloud-debug');
exports.getPublishedComments = function getPublishedComments(req, res) {
    connection.query("select * from ratings where published=true",
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result);
	    }
	});
};

import('@google/cloud-debug');
exports.getPublishedCommentsOfAccommodation = function getPublishedCommentsOfAccommodation(req, res) {
    connection.query("select * from ratings where published=true and accommodationID="+req.query.accommodationID,
	(err, result)=> {
	    if (err) res.status(400).send('nije dobro getovanje odredjenog');
	    else {
		res.status(200).send(result);
	    }
	});
};


