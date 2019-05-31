/**
 * 
 * HTTP cloud function provides greeting feature. Responds to any HTTP request
 * that can provide "name" field in HTTP body or as a request parameter.
 *
 * @param {!Object} req Cloud Function request context.
 * @param {!Object} res Cloud Function response context.
 */
 
import('@google/cloud-debug');
exports.helloHttp = function helloHttp(req, res) {
  
  // Example input: ?name=Pera or {"name": "Pera"}
  let name = req.query.name || req.body.name;
  
  if (name === undefined) {
    // This is an error case, as "name" is required.
    console.warn('Bad request: No name provided.');
    res.status(400).send('Name is not defined!');
  } else {
    // Everything is okay.
    console.log('Sending a greeting to: ' + name);
    res.status(200).send('Hello ' + name + '!');
  }
};
