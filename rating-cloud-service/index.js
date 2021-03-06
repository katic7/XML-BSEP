const connection = require('./database')

exports.newRating = function newRating(req, res) {
    let userID = req.body.userID;
    let comment = req.body.comment;
    let rating = req.body.rating;
    let accommodationID = req.body.accommodationID;
	let reservationID = req.body.reservationID;
    connection.query("insert into ratings (userID, comment, rating, accommodationID, published, reservationID) values (?, ?, ?, ?, ?, ?)", [userID, comment, rating, accommodationID, 0, reservationID], (err, result) => {
	if (err) res.status(400).send(err);
	else {
		
		res.status(200).send('upisano');

	}
	
    });
};

exports.getAllRatings = function getAllRatings(req, res) {
    connection.query("select * from ratings", (err, result)=> {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
    });
};


exports.getSpecificRating = function getSpecificRating(req, res) {
    connection.query("select id, userID, comment, rating, accommodationID, published from ratings where id="+req.query.id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result[0]);
	    }
	});
};


exports.getRatingsFromSpecificAcc = function getRatingsFromSpecificAcc(req, res) {
    connection.query("select * from ratings where accommodationID="+req.query.accommodationID,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result);
	    }
	});
};


exports.getRatingScore = function getRatingScore(req, res) {
    connection.query("select avg(rating) as ratingScore from ratings where accommodationID="+req.query.id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result[0]);
	    }
	});
};


exports.publishComment = function publishComment(req, res) {
    let flag = req.body.flag;
    let id = req.body.id;
    
    connection.query("UPDATE `ratings` SET `published` = "+flag+" where id = "+id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send("Successfully updated");
	    }
	});
};


exports.getUnpublishedComments = function getUnpublishedComments(req, res) {
    connection.query("select * from ratings where published=false",
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result);
	    }
	});
};



exports.getPublishedComments = function getPublishedComments(req, res) {
    connection.query("select * from ratings where published=true",
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result);
	    }
	});
};


exports.getPublishedCommentsOfAccommodation = function getPublishedCommentsOfAccommodation(req, res) {
    connection.query("select * from ratings where published=true and accommodationID="+req.query.accommodationID,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result);
	    }
	});
};

exports.helloWorld = (req, res) => {
  res.send('Hello, World');
};


