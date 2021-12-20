// let myHeaders = new Headers();

// myHeaders.append("x-rapidapi-key", "c45c61974b8c98906b84850486e6ef4e");
// myHeaders.append("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");

// var requestOptions = {
//     method: "GET",
//     headers: myHeaders,
//     redirect: "follow",
// };

export const getTeams = async () => {
  // Free API const urlTeams = "https://pokeapi.co/api/v2/pokemon/1";
  url = "https://v3.football.api-sports.io/teams?country=spain&league=140&season=2020";
  await fetch (urlTeams)
  .then(response => response.json() )
  .then(data => {
    console.log(data)
  })
  .catch(err=>console.log(err))
}