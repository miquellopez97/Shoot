let myHeaders = new Headers();

myHeaders.append("x-rapidapi-key", "c45c61974b8c98906b84850486e6ef4e");
myHeaders.append("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");

var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
};

document.getElementById(`cum`).addEventListener("click", async () => {
    const urlTeams = "https://v3.football.api-sports.io/teams?country=spain&league=140&season=2020";
    // Free API 
    const urlfree = "https://pokeapi.co/api/v2/pokemon/1";
    await fetch (urlfree, requestOptions)
    .then(response => response.json() )
    .then(data => {
        const element = document.getElementById("container")
        element.innerHTML = `<p>${data.name}</p>`;
    })
    .catch(err=>console.log(err))
})



