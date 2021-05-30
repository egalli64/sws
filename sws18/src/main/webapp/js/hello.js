document.getElementById('message').onclick = () => {
	let server = document.getElementById("localhost").checked ? "localhost" : "127.0.0.1";
	let url = "http://" + server + ":8080/hello";
	console.log("Fetching " + url);
	fetch(url)
		.then(response => {
			if (!response.ok) {
				throw new Error('Negative response from network');
			}
			return response.json();
		}).then(result => {
			console.log(result); 
		}).catch(err => {
			console.log('No answer. ' + err);
		});
};
