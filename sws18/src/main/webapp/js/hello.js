document.getElementById('message').onclick = () => {
	fetch("hello")
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
