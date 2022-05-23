const url = new URL(location.href);

let searched_word = document.getElementById("search_word").value = url.searchParams.get("word");

document.getElementById("search_btn").addEventListener("click", ()=> {
	document.getElementById("search_btn").href = "searchComments?word=" + document.getElementById("search_word").value;
})

for (let i = 0; i < document.getElementsByName("searched_comment").length; i++) {
	document.getElementsByName("searched_title")[i].setAttribute("id",`title${i + 1}`);
	document.getElementsByName("searched_comment")[i].setAttribute("id",`comment${i + 1}`);

	let searched_title =  document.getElementById(`title${i + 1}`);
	let searched_comment =  document.getElementById(`comment${i + 1}`);

	searched_title.innerHTML = searched_title.innerText.replace(searched_word, `<span style="background-color: rgb(52, 224, 37)">${searched_word}</span>`);
	searched_comment.innerHTML = searched_comment.innerText.replace(searched_word, `<span style="background-color: rgb(52, 224, 37)">${searched_word}</span>`);
}