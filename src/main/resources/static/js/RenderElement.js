
export async function renderBook(id, item) {

    const book = await (await fetch(`/api/explore/book/${id}`)).json();

    let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-muted">Title: <span class="h4 text-white">${book.title}</span> </p> 
    <p class="text-muted">Rating: <span class="h5 text-white">${book.rating} / 5</span> </p> 
    <img src="https://image.shutterstock.com/image-illustration/chinese-style-fantasy-scenes3d-rendering-600w-647884516.jpg" class="img-fluid" alt="Responsive image">
    <br>  <br>
    <p class="text-muted">Author: <span class="h5 text-white">${book.author}</span> </p>              
                  <p class="text-muted">Description: </p>
                    <p class="border border-dark p-3">${book.description}</p>
                 
                   <p class="text-muted">Release Date: <span class="p text-white">${book.releaseDate}</span> </p>
                   <br>`;
    result+= renderRatingForm(id,item);
    result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
        + renderButtons(id,item) +
        `</div>`;

    if (book.comments.length > 0) {
        result += `<br><p class="text-muted">Comments: </p>`;
        book.comments.forEach(c => {
            result += `
            <div class="border border-dark>
            <p class="text-muted">Comment By: <span class="h5 text-light">${c.user}</span> </p> 
            <p class="text-muted">On: <span class="p text-light">${c.publishDate}</span> </p>
            <div>
            <p class="text-white">${c.content}</p></div>`
                + `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
                + renderCommentButtons(c.id, item, id) +
                `</div></div>`;
        })
    }
    result += `<form class="mx-auto w-50" action='/comment/book/${book.id}' method="post">
<div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Your comment:</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content"></textarea>
        </div>
<button type="submit" class="btn btn-dark">Post</button>
</form>`


    result += "</div></div>"
    return result;
}
export async function renderMovie(id, item) {
    const movie =await(await fetch(`/api/explore/movie/${id}`)).json();

    let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-muted">Title: <span class="h4 text-white">${movie.title}</span> </p> 
    <p class="text-muted">Rating: <span class="h5 text-white">${movie.rating} / 5</span> </p> 
   <iframe width="560" height="315" src="https://www.youtube.com/embed/ndl1W4ltcmg" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    <br>  <br>
    <p class="text-muted">Cast: <span class="h5 text-white">${movie.cast}</span> </p>              
    <p class="text-muted">Director: <span class="h5 text-white">${movie.director}</span> </p>              
                  <p class="text-muted">Description: </p>
                    <p class="border border-dark p-3">${movie.description}</p>
                 
                   <p class="text-muted">Release Date: <span class="p text-white">${movie.releaseDate}</span> </p>
                   <br>`;
    result+= renderRatingForm(id,item);
    result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
        + renderButtons(id,item) +
        `</div>`;

    if (movie.comments.length > 0) {
        result += `<br><p class="text-muted">Comments: </p>`;
        movie.comments.forEach(c => {
            result += `
            <div class="border border-dark>
            <p class="text-muted">Comment By: <span class="h5 text-light">${c.user}</span> </p> 
            <p class="text-muted">On: <span class="p text-light">${c.publishDate}</span> </p>
            <div>
            <p class="text-white">${c.content}</p></div>`
                + `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
                + renderCommentButtons(c.id, item, id) +
                `</div></div>`;
        })
    }
    result += `<form class="mx-auto w-50" action='/comment/movie/${movie.id}' method="post">
<div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Your comment:</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content"></textarea>
        </div>
<button type="submit" class="btn btn-dark">Post</button>
</form>`


    result += "</div></div>"
    return result;

}
 export async function renderStory(id, item) {
     const story = await(await fetch(`/api/explore/story/${id}`)).json();
     let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-muted">Title: <span class="h4 text-white">${story.title}</span> </p> 
    <p class="text-muted">Rating: <span class="h5 text-white">${story.rating} / 5</span> </p> 
   <p class="text-muted">Short Summary: </p>
                    <p class="border border-dark p-3">${story.summary}</p>
    <br>          
    <p class="text-muted">Author: <span class="h5 text-white">${story.user}</span> </p>      
       <p class="text-muted">Release Date: <span class="p text-white">${story.addedOn}</span> </p>  <br>      
                  <p class="text-muted">Enjoy your read...: </p>
                    <p class="border border-dark p-3">${story.content}</p>
                 
                
                   <br>`;
     result+= renderRatingForm(id,item);
     result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
         + renderButtons(id,item) +
         `</div>`;

     if (story.comments.length > 0) {
         result += `<br><p class="text-muted">Comments: </p>`;
         story.comments.forEach(c => {
             result += `
            <div class="border border-dark>
            <p class="text-muted">Comment By: <span class="h5 text-light">${c.user}</span> </p> 
            <p class="text-muted">On: <span class="p text-light">${c.publishDate}</span> </p>
            <div>
            <p class="text-white">${c.content}</p></div>`
                 + `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
                 + renderCommentButtons(c.id, item, id) +
                 `</div></div>`;
         })
     }
     result += `<form class="mx-auto w-50" action='/comment/story/${story.id}' method="post">
<div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Your comment:</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content"></textarea>
        </div>
<button type="submit" class="btn btn-dark">Post</button>
</form>`


     result += "</div></div>"
     return result;

 }
 export async function renderQuestion(id, item) {

     const question = await(await fetch(`/api/explore/question/${id}`)).json();
     let result = `<div class="container">
    <div class="mx-auto w-100 text-center text-white bg-text">
    
    <p class="text-muted">Title: <span class="h4 text-white">${question.title}</span> </p> 
       
    <p class="text-muted">By: <span class="h5 text-white">${question.user}</span> </p>      
       <p class="text-muted">Release Date: <span class="p text-white">${question.publishDate}</span> </p>  <br>      
                  <p class="text-muted">Question:</p>
                    <p class="border border-dark p-3">${question.content}</p>
                 
                
                   <br>`;

     result += `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
         + renderButtons(id,item) +
         `</div>`;

     if (question.answers.length > 0) {
         result += `<br><p class="text-muted">Answers: </p>`;
         question.answers.forEach(c => {
             result += `
            <div class="border border-dark>
            <p class="text-muted">Answer By: <span class="h5 text-light">${c.user}</span> </p> 
            <p class="text-muted">On: <span class="p text-light">${c.publishDate}</span> </p>
            <div>
            <p class="text-white">${c.content}</p></div>`
                 + `<div sec:authorize=\"hasAuthority('MODERATOR')\" class=\"text-white\">`
                 + renderCommentButtons(c.id, item, id) +
                 `</div></div>`;
         })
     }
     result += `<form class="mx-auto w-50" action='/comment/question/${question.id}' method="post">
<div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Your answer:</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content"></textarea>
        </div>
<button type="submit" class="btn btn-dark">Post</button>
</form>`


     result += "</div></div>"
     return result;
 }
export function RenderEditComment(commentId, prevText, item, id ) {

    return `
            <form class="mx-auto w-50" action='/comment/edit/${item}/${id}/${commentId}}' method="post">
           <div class="form-group">      
            <textarea type="text" class="form-control" rows="3" id="content" name="content">${prevText}</textarea>
        </div>
<button type="submit" class="btn btn-outline-danger btn-sm">Done</button>
<button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
</form>`;

}
export async function RenderEditBook(id) {
    const book = await (await fetch(`/api/explore/book/${id}`)).json();
    return `
            <form class="mx-auto w-50" action="/edit/book/${id}" method="post">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" value="${book.title}" name="title"/>
        </div>
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="image" class="h4 mb-2 text-white" >Image...todo</label>
            </div>
            <input type="text" class="form-control" id="image" value="${book.image}" name="image"/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="description" class="h4 mb-2">Description</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="description"  name="description">${book.description}</textarea>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="author" class="h4 mb-2">Author name</label>
            </div>
            <input type="text" class="form-control" id="author"  value="${book.author}" name="author"/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="releaseDate" class="h4 mb-2">Release Date</label>
            </div>
            <input type="date" class="form-control" id="releaseDate" value="${book.releaseDate}" name="releaseDate"/>
        </div>
        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>
       `;
}
export async function RenderEditMovie(id) {
    const movie =await(await fetch(`/api/explore/movie/${id}`)).json();
return `<form class="mx-auto w-50" action="/edit/movie/${id}" method="post">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" name="title" value="${movie.title}"/>
        </div>
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="trailerLink" class="h4 mb-2 text-white">Video Trailer Link</label>
            </div>
            <input type="text" class="form-control" id="trailerLink" name="trailerLink" value="${movie.trailerLink}"/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="description" class="h4 mb-2">Description</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="description" name="description">${movie.description}</textarea>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="director" class="h4 mb-2">Director</label>
            </div>
            <input type="text" class="form-control" id="director" name="director" value="${movie.director}"/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="cast" class="h4 mb-2">Cast</label>
            </div>
            <input type="text" class="form-control" id="cast" name="cast" value="${movie.cast}"/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="releaseDate"  class="h4 mb-2">Release Date</label>
            </div>
            <input type="date" class="form-control" id="releaseDate" name="releaseDate" value="${movie.releaseDate}"/>
        </div>
        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>`

 }
 export async function RenderEditStory(id) {
     const story = await(await fetch(`/api/explore/story/${id}`)).json();

     return `
     <form class="mx-auto w-50" action="/edit/story/${id}" method="post">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" name="title" value="${story.title}"/>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="summary" class="h4 mb-2">Summary</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="summary" name="summary">${story.summary}</textarea>
        </div>
        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Content</label>
            </div>
            <textarea type="text" class="form-control" rows="15"  id="content" name="content">${story.content}</textarea>
        </div>
        <div class="button-holder d-flex justify-content-center">
            <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>
     `;
 }
 export async function RenderEditQuestion(id) {
     const question = await(await fetch(`/api/explore/question/${id}`)).json();

     return `
     <form class="mx-auto w-50" action="/edit/question/${id}" method="post">
        <div class="form-group">
            <div class="label-holder textCol d-flex justify-content-center">
                <label for="title" class="h4 mb-2 text-white">Title</label>
            </div>
            <input type="text" class="form-control" id="title" name="title" value="${question.title}"/>
        </div>

        <div class="form-group">
            <div class="label-holder text-white textCol d-flex justify-content-center">
                <label for="content" class="h4 mb-2">Type your question</label>
            </div>
            <textarea type="text" class="form-control" rows="3" id="content" name="content">${question.content}</textarea>
        </div>
        <div class="button-holder d-flex justify-content-center">
             <button class="btn btn-outline-danger btn-sm" type="submit">Done</button>
            <button type="button" class="btn btn-outline-danger btn-sm" id="cancel">Cancel</button>
        </div>
    </form>
     `;

 }
 function renderRatingForm(id,item) {
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
 function renderButtons(id,item) {
     return `<button type="button" class="btn btn-outline-danger btn-sm" id="editItem">Edit</button>
      <form action='/delete/${item}/${id}' method="post">
       <button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
</form>`


 }
 function renderCommentButtons(commentId, item,id) {
     return `<button type="button" class="btn btn-outline-danger btn-sm" id="editComment" value="${commentId}">Edit</button>
        <form action='/comment/delete/${item}/${id}/${commentId}' method="post">
       <button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
</form>`;

 }
