
const items =(window.location.pathname.toString().split('/'))[2];


const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};
window.addEventListener('load', async function () {

    const targetDiv = document.getElementById('exploreItems');
    await renderItems();

    async function renderItems() {
        let result = '';
        if (items === 'books'){
           result = await renderBooks();
        } else if (items === 'movies'){
            result =  await renderMovies();
        } else if (items === 'stories'){
            result =  await renderStories();
        } else if (items === 'questions'){
            result =  await renderQuestions()
        }else if (items === 'quotes'){
            result =  await renderQuotes();
            targetDiv.addEventListener('click', eventHandler)
        }
        targetDiv.innerHTML = result;
    };

    async function renderMovies() {
        const movies = await (await fetch('/api/explore/movies')).json();
        let result = `<div class='row mb-4 d-flex justify-content-around'>`;

        movies.forEach(movie=> {
            result += '<div class="col-md-4 d-flex flex-column bg-text mb-3">';
            result += `<a href="/explore/movie/${movie.id}">`
            result += `<p class="text-muted">Title: <u><span class="h4 text-white">${movie.title}</span></u> </p> 
           <p class="text-muted">Rating: <span class="p text-white">${movie.rating} / 5</span> </p> 
             <p class="text-muted">Cast: <span class="p text-white">${movie.cast}</span> </p>`
            result += '</a></div>';
        });
        result += '</div>';

        return result;
    }
   async function renderStories() {
        const stories = await (await fetch('/api/explore/stories')).json();
       let result = `<div class='row mb-4 d-flex justify-content-around'>`;

       stories.forEach(story=> {
           result += '<div class="col-md-4 d-flex flex-column bg-text mb-3">';
           result += `<a href="/explore/story/${story.id}">`
           result += `<p class="text-muted">Title: <u><span class="h4 text-white">${story.title}</span></u> </p> 
           <p class="text-muted">Rating: <span class="p text-white">${story.rating} / 5</span> </p> 
             <p class="text-muted">Author: <span class="p text-white">${story.user}</span> </p>`
           result += '</a></div>';
       });
       result += '</div>';

       return result;
    }
    async function renderQuestions() {
        const questions = await (await fetch('/api/explore/questions')).json();
        let result = `
  <table class="table table-bordered bg-text justify-content-around" id="table" >
  <thead>
    <tr>
      <th scope="col">Title:</th>
      <th scope="col">Published On</th>
      <th scope="col">From</th>
      <th scope="col">Answered</th>
    </tr>
  </thead>
  <tbody>`;

        questions.forEach(question => {
            result += `<tr>
> 
      <td><a href="/explore/question/${question.id}">${question.title}</a></td>
      <td>${question.publishDate}</td>
      <td>${question.user}</td>
      <td>${question.answersCount}</td>
    </tr>`;
        });
        result += ' </tbody>\n' +
            '</table>';

        return result;
    }
    async function renderQuotes() {
        const quotes = await (await fetch('/api/explore/quotes')).json();
        let result = `  <br><div class='class="text-center"'>`;

        quotes.forEach(quote => {
            result += `<div class="text-white>
          <div class="blockquote text-right ">
  <p class="mb-0 text-white"><em>&#65282;<span>${quote.content}</span>&#65282;</em></p>
  <p class="blockquote-footer text-info">${quote.author}</p>
  <div sec:authorize=\\"hasAuthority('MODERATOR')\\" class=\\"text-white\\">
<button type="button" class="btn btn-outline-danger btn-sm editQuoteButtons" id="editQuote" value="${quote.id}" >Edit</button>
      <form action='/delete/quote/${quote.id}' method="post">
       <button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
</form></div>
</div>
</div> <br>`;
        });
        result += '</div>';

        return result;
    }
     async function renderBooks(){
         const books = await (await fetch('/api/explore/books')).json();

        let result = `<div class='row mb-4 d-flex justify-content-around'>`;

        books.forEach(book=> {
            result += '<div class="col-md-4 d-flex flex-column bg-text mb-3">';
            result += `<a href="/explore/book/${book.id}">`
            result += `<p class="text-muted">Title: <u><span class="h4 text-white">${book.title}</span></u> </p> 
           <p class="text-muted">Rating: <span class="p text-white">${book.rating} / 5</span> </p> 
             <p class="text-muted">Author: <span class="p text-white">${book.author}</span> </p>`
            result += '</a></div>';
        });
        result += '</div>';

        return result;

    }
    async function eventHandler(e) {
        const target = e.target;
        if (target.tagName !== 'BUTTON') {
            return;
        }
        loader.show();
        if (target.id === 'editQuote') {
            await renderEditQuote(e);
            loader.hide();
        } else if (target.id === 'cancel') {
            await renderItems();
            loader.hide();
        }

    }
    async function renderEditQuote(e) {
        const currentDiv = e.target.parentElement.parentElement;
        const quoteId = e.target.value;
        const defAuthor = currentDiv.children[1].innerText;
        const defQuote = (currentDiv.children[0].textContent).slice(1,-1);

        console.log(defQuote)
        currentDiv.innerHTML = `
          <form class="text-right text-white w-50" action="/edit/quote/${quoteId}" method="post">
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Quote:</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content">${defQuote}</textarea>
        </div>
         <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="author" class="h4 mb-2 text-white">Author</label>
            </div>
            <input type="text" class="form-control" id="author"  value="${defAuthor}" name="author"/>
        </div>

        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>`;


    }



})