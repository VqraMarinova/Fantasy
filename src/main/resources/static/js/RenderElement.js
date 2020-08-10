export async function renderBook(id, item) {
    let book;
    try {
         book = await (await fetch(`/api/explore/book/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This book does not exist</div></div>';
    }



    let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-info">Title: <span class="h4 text-white">${book.title}</span> </p> 
    <p class="text-info">Rating: <span class="h5 text-white">${book.rating} / 5</span> </p> 
    <img src="${book.image}" class="img-thumbnail img-fluid max-width: 100% height: auto" id="bookImage" alt="Responsive image">
    <br>  <br>
    <p class="text-info">Author: <span class="h5 text-white">${book.author}</span> </p>              
                  <p class="text-info">Description: </p>
                    <p class="border border-dark p-3">${book.description}</p>
                 
                   <p class="text-info">Release Date: <span class="p text-white">${book.releaseDate}</span> </p>
                   <br>`;
    if (book.canVote) {
        result += renderRatingForm(id, item);
    } else {
        result+= '<p class="text-white">You have already rated this book</p>';
    }
    result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
        + renderButtons(id, item) +
        `</div>`;

    if (book.comments.length > 0) {
        result += renderComments(book.comments, item, id);
    }
    result += addComment(item, id);

    result += "</div></div>"
    return result;
}

export async function renderMovie(id, item) {
    let movie;
    try {
        movie = await (await fetch(`/api/explore/movie/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This movie does not exist</div></div>';
    }


    let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-info">Title: <span class="h4 text-white">${movie.title}</span> </p> 
    <p class="text-info">Rating: <span class="h5 text-white">${movie.rating} / 5</span> </p> 
   <iframe width="560" height="315" src="${movie.trailerLink}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    <br>  <br>
    <p class="text-info">Cast: <span class="h5 text-white">${movie.cast}</span> </p>              
    <p class="text-info">Director: <span class="h5 text-white">${movie.director}</span> </p>              
                  <p class="text-info">Description: </p>
                    <p class="border border-dark p-3">${movie.description}</p>
                 
                   <p class="text-info">Release Date: <span class="p text-white">${movie.releaseDate}</span> </p>
                   <br>`;


    if (movie.canVote) {
        result += renderRatingForm(id, item);
    } else {
        result+= '<p class="text-white">You have already rated this movie</p>';
    }

    result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
        + renderButtons(id, item) +
        `</div>`;

    if (movie.comments.length > 0) {
        result += renderComments(movie.comments, item, id);
    }
    result += addComment(item, id);

    result += "</div></div>"
    return result;
}

export async function renderStory(id, item) {
    let story;
    try {
        story = await (await fetch(`/api/explore/story/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This story does not exist</div></div>';
    }

    let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-info">Title: <span class="h4 text-white">${story.title}</span> </p> 
    <p class="text-info">Rating: <span class="h5 text-white">${story.rating} / 5</span> </p> 
   <p class="text-info">Short Summary: </p>
                    <p class="border border-dark p-3">${story.summary}</p>
    <br>          
    <p class="text-info">Author: <span class="h5 text-white">${story.user}</span> </p>      
       <p class="text-info">Release Date: <span class="p text-white">${story.addedOn}</span> </p>  <br>      
                  <p class="text-info">Enjoy your read...: </p>
                    <p class="border border-dark p-3">${story.content}</p>
                 
                
                   <br>`;


    if (story.canVote) {
        result += renderRatingForm(id, item);
    } else {
        result+= '<p class="text-white">You have already rated this story</p>';
    }
    result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
        + renderButtons(id, item) +
        `</div>`;

    if (story.comments.length > 0) {
        result += renderComments(story.comments, item, id);
    }
    result += addComment(item, id);

    result += "</div></div>"
    return result;
}

export async function renderQuestion(id, item) {

    let question;
    try {
        question = await (await fetch(`/api/explore/question/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This question does not exist</div></div>';
    }

    let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-info">Title: <span class="h4 text-white">${question.title}</span> </p> 
       
    <p class="text-info">By: <span class="h5 text-white">${question.user}</span> </p>      
       <p class="text-info">Release Date: <span class="p text-white">${question.publishDate}</span> </p>  <br>      
                  <p class="text-info">Question:</p>
                    <p class="border border-dark p-3">${question.content}</p>
                 
                
                   <br>`;

    result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
        + renderButtons(id, item) +
        `</div>`;

    if (question.answers.length > 0) {
        result += renderComments(question.answers, item, id);
    }
    result += addComment(item, id);

    result += "</div></div>"
    return result;
}

export function RenderEditComment(commentId, prevText, item, id) {

    return `
            <form class="mx-auto w-50" action='/comment/edit/${item}/${id}/${commentId}' method="post" th:object="\${commentModel}" enctype="multipart/form-data">
           <div class="form-group">      
            <textarea type="text" class="form-control" rows="3" id="content" name="content"
              th:field="*{content}" maxlength = "600"  minlength = "10" required>${prevText}</textarea>
        </div>
<button type="submit" class="btn btn-outline-danger btn-sm">Done</button>
<button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
</form>`;

}

export async function RenderEditBook(id) {
    let book;
    try {
        book = await (await fetch(`/api/explore/book/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This book does not exist</div></div>';
    }
    return `
            <form class="mx-auto w-50" action="/edit/book/${id}" method="post" th:object="\${bookModel}" enctype="multipart/form-data">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" value="${book.title}" name="title" th:field="*{title}" maxlength = "100"  minlength = "5" required/>
        </div>
       <br>
        <div class="form-group text-center">
            <br>
                <label for="image" class="h6 mb-2 text-white">Upload Book, book cover or other related image(Optional, PNG or JPG only, max 10 MB):</label>
            <div class="textCol d-flex justify-content-center">
            <input type="file"  id="image" name="image" th:field="*{image}"/>
            </div><br>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="description" class="h4 mb-2">Description</label>
            </div>
            <textarea type="text" th:field="*{description}" class="form-control" rows="3" id="description"  name="description" maxlength = "600"  minlength = "30" required>${book.description}</textarea>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="author" class="h4 mb-2">Author name</label>
            </div>
            <input type="text" th:field="*{author}" class="form-control" id="author"  value="${book.author}" name="author" maxlength = "100"  minlength = "5" required/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="releaseDate" class="h4 mb-2">Release Date</label>
            </div>
            <input type="date"  th:field="*{releaseDate}" class="form-control" id="releaseDate" value="${book.releaseDate}" th:field="*{releaseDate}" required name="releaseDate" required/>
        </div>
        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>
       `;
}

export async function RenderEditMovie(id) {
    let movie;
    try {
        movie = await (await fetch(`/api/explore/movie/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This movie does not exist</div></div>';
    }
    return `<form class="mx-auto w-50" action="/edit/movie/${id}" method="post" th:object="\${movieModel}">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" name="title" value="${movie.title}" th:field="*{title}" maxlength = "100"  minlength = "5" required/>
        </div>
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="trailerLink" class="h4 mb-2 text-white">Video Trailer Link</label>
            </div>
            <input type="text" class="form-control" id="trailerLink" name="trailerLink" value="${movie.trailerLink}" th:field="*{trailerLink}" placeholder="https://www.youtube.com/..."/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="description" class="h4 mb-2">Description</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="description" name="description"  th:field="*{description}" maxlength = "600"  minlength = "30" required>${movie.description}</textarea>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="director" class="h4 mb-2">Director</label>
            </div>
            <input type="text" class="form-control" id="director" name="director" value="${movie.director}" th:field="*{director}" maxlength = "100"  minlength = "5" required/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="cast" class="h4 mb-2">Cast</label>
            </div>
            <input type="text" class="form-control" id="cast" name="cast" value="${movie.cast}"  th:field="*{cast}" maxlength = "250"  minlength = "5" required/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="releaseDate"  class="h4 mb-2">Release Date</label>
            </div>
            <input type="date" class="form-control" id="releaseDate" name="releaseDate" value="${movie.releaseDate}" th:field="*{releaseDate}" required/>
        </div>
        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>`

}

export async function RenderEditStory(id) {
    let story;
    try {
        story = await (await fetch(`/api/explore/story/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This story does not exist</div></div>';
    }

    return `
     <form class="mx-auto w-50" action="/edit/story/${id}" method="post" th:object="\${storyModel}">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" name="title" value="${story.title}"  th:field="*{title}"  maxlength = "100"  minlength = "5" required/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="summary" class="h4 mb-2">Summary</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="summary" name="summary" th:field="*{summary}"  maxlength = "600"  minlength = "30" required>${story.summary}</textarea>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Content</label>
            </div>
            <textarea type="text" class="form-control" rows="15"  id="content" name="content" th:field="*{content}"  maxlength = "7000"  minlength = "400" required>${story.content}</textarea>
        </div>
        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>
     `;
}

export async function RenderEditQuestion(id) {
    let question;
    try {
        question = await (await fetch(`/api/explore/question/${id}`)).json();
    } catch (e) {
        return '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">This question does not exist</div></div>';
    }

    return `
     <form class="mx-auto w-50" action="/edit/question/${id}" method="post" th:object="\${questionModel}">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" name="title" value="${question.title}"  th:field="*{title}" maxlength = "100"  minlength = "5" required/>
        </div>

        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Type your question</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content" th:field="*{content}" maxlength = "800"  minlength = "30" required>${question.content}</textarea>
        </div>
        <div class="button-holder d-flex justify-content-center">
             <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>
     `;

}

function renderRatingForm(id, item) {
    return `<form action="/rate/${item}/${id}" class="form-check-inline" method="post">
<div class="form-check form-check-inline">
  <input type="radio" class="form-check-input" name="rating" value="1">
  <label class="form-check-label" for="materialInline1">1</label>
</div>
<div class="form-check form-check-inline">
  <input type="radio" class="form-check-input" name="rating" value="2">
  <label class="form-check-label" for="materialInline2">2</label>
</div>
<div class="form-check form-check-inline">
  <input type="radio" class="form-check-input" name="rating" value="3">
  <label class="form-check-label" for="materialInline3">3</label>
</div>
<div class="form-check form-check-inline">
  <input type="radio" class="form-check-input" name="rating" value="4">
  <label class="form-check-label" for="materialInline3">4</label>
</div>
<div class="form-check form-check-inline">
  <input type="radio" class="form-check-input" name="rating" value="5">
  <label class="form-check-label" for="materialInline3">5</label>
</div>
<div>
<button type="submit" class="btn btn-light btn-sm rateBtn">Rate</button>
</div>
</form><br>`;

}

function renderButtons(id, item) {
    return `<button type="button" class="btn btn-outline-danger btn-sm" id="editItem">Edit</button>
      <form action='/delete/${item}/${id}' method="post">
       <button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
</form>`


}

function renderCommentButtons(commentId, item, id) {
    return `<button type="button" class="btn btn-outline-danger btn-sm" id="editComment" value="${commentId}">Edit</button>
        <form action='/comment/delete/${item}/${id}/${commentId}' method="post">
       <button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
</form>`;

}

function renderComments(comments, item, id) {
    let result = `<br><p class="text-info">Comments: </p>`;
    comments.forEach(c => {
        result += `
            <div class="border border-dark>
            <p class="text-info">Comment By: <span class="h5 text-light">${c.user}</span> </p> 
            <p class="text-info">On: <span class="p text-light">${c.publishDate}</span> </p>
            <div>
            <p class="text-white">${c.content}</p></div>`
            + `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
            + renderCommentButtons(c.id, item, id) +
            `</div></div>`;
    })
    return result;
}

function addComment(item, id) {
    let result = `<form class="mx-auto w-50" action='/comment/${item}/${id}' method="post" th:object="\${commentModel}" enctype="multipart/form-data">
<div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Your comment:</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content"
            th:field="*{content}" maxlength = "600"  minlength = "10" required></textarea>
        </div>
<button type="submit" class="btn btn-dark">Post</button>
</form>`
    return result;
}

