const url = new URL(location.href);

document.getElementById("search_btn").addEventListener("click", ()=> {
	document.getElementById("search_btn").href = "searchComments?word=" + document.getElementById("search_word").value;
})

document.getElementById("search_word").value = url.searchParams.get("word");