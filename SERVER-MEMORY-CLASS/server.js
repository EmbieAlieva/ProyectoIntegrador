const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb+srv://usermemoryapp:cuatrovientos@cluster0.vlc2d.mongodb.net/punctuationdb?retryWrites=true&w=majority', { useNewUrlParser: true, useUnifiedTopology: true })

let punctuationSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minlength: 1
    },
    dificulty: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    time: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    moves: {
        type: Number,
        required: true,
        minlength: 1,
        trim: true
    },
    points: {
        type: Number,
        required: true,
        minlength: 1,
        trim: true
    }

});
/* 
let Punctuation = mongoose.model('punctuations', punctuationSchema);

    memoryURL = 'https://servermemoryapp.herokuapp.com/punctuations';

}*/

let app = express();

app.get("/punctuations", (req, res) => {

    Punctuation.find().then(result => {
        res.send(result);
    });

});
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*"); // update to match the domain you will make the request from
    res.header("Access-Control-Allow-Methods", 'GET,PUT,POST,DELETE,OPTIONS');
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});
app.get("/punctuations/:id", (req, res) => {
    let data;
    Punctuation.findById(req.params.id).then(result => {

        if (result) {
            data = { error: false, result: result };
        } else {
            data = { error: true, errorMessage: 'Punctuation not found' }
        }

        res.send(data);
    }).catch(error => {
        data = { error: true, errorMessage: "Error getting Punctuation" }
        res.send(data);
    });
});

app.use(bodyParser.json());

app.post("/punctuations", (req, res) => {

    let newPunctuation = new Punctuation({
        name: req.body.name,
        dificulty: req.body.dificulty,
        time: req.body.time,
        moves: req.body.moves,
        points: req.body.points,
    });
    newPunctuation.save().then(result => {
        let data = { error: false, result: result }
        res.send(data);
    }).catch(error => {
        let data = { error: true, errorMessage: "Error adding punctuation" }
        res.send(data);
    });
});

app.delete("/punctuations/:id", (req, res) => {
    Punctuation.findOneAndDelete(req.params.id).then(result => {
        let data = { error: false, result: result };
        res.send(data);
    }).catch(error => {
        let data = { error: true, errorMessage: "error deleting punctuation" };
        res.send(data);
    });
});

app.delete('/', (req, res) => {
    res.send("DELETE Request Called")
})

/*
app.listen(8080);
*/
// start the server listening for requests
app.listen(process.env.PORT || 3000,
    () => console.log("Server is running..."));