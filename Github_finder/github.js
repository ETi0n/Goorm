class Github{
    constructor(){
        this.client_id = 'Iv1.4c6ec1df827b185f';
        this.client_secret = '6eb298b4914ec324b9572588bb5b8433bed0d527';
        this.repos_count = 5;
        this.repos_sort = 'created: asc';
    }

    async getUser(user){
        const profileResponse = fetch(`http://api.github.com/users/${user}?client_id=${this.client_id}&client_secret=${this.client_secret}`);
        console.log(profileResponse);

        const repoRespone = await fetch(`https://api.github.com/users/${user}/repos?per_page=${this.repos_count}&sort=${this.repos_sort}&client_id=${his.client_id}&client_secret=${this.client_secret}`);
        console.log(repoRespone);

        const profile = await profileResponse.json();
        const repos = await repoRespone.json();

        return{
            profile,
            repos
        }
    }
}