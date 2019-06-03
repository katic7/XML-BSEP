const connection = require('./database')

import('@google/cloud-debug');
exports.newRating = function newRating(req, res) {
    let userID = req.body.userID;
    let comment = req.body.comment;
    let rating = req.body.rating;
    let accommodationID = req.body.accommodationID;

    connection.query("insert into ratings (userID, comment, rating, accommodationID) values (?, ?, ?, ?)",[userID, comment, rating, accommodationID], (err, result) => {
        if (err) res.status(400).send('nije dobro upisivanje');
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
    connection.query("select id, userID, comment, rating, accommodationID from ratings where id="+req.query.id,
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
