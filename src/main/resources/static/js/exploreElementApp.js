const [item, id] =(window.location.pathname.toString().split('/')).slice(-2);

import * as render from './RenderElement.js'




window.addEventListener('load', async function () {
    const targetDiv = document.getElementById('exploreItems');

    targetDiv.innerHTML = '<div class="container"> <div class=\"mx-auto w-100 text-center text-white bg-text\">Loading...</div></div>';

    await renderItem();

   async function renderItem() {
       let result = '';
        if (item === 'book'){
            result = await render.renderBook(id, item);
        } else if (item === 'movie'){
            result = await render.renderMovie(id, item);
        } else if (item === 'story'){
            result = await render.renderStory(id, item);
        } else if (item === 'question'){
            result = await render.renderQuestion(id, item);
        }
        targetDiv.innerHTML = result;

    }


    targetDiv.addEventListener('click', async (e)=>{
        const target = e.target;
        if (target.tagName !== 'BUTTON'){
            return;
        }

        if (target.id === 'editItem'){
           targetDiv.innerHTML = await EditHandler();

        }else if (target.id === 'editComment'){
            const commentId = target.value;
            const div = target.parentElement.previousSibling;
            const prevText = div.innerText;
            div.lastChild.remove();
            div.nextSibling.remove();
            div.innerHTML= render.RenderEditComment(commentId, prevText, item,id);

        } else if (target.id === 'cancel') {
           await renderItem();

        }
    });


    function EditHandler() {

        if (item === 'book'){
         return render.RenderEditBook(id);
        } else if (item === 'movie'){
           return render.RenderEditMovie(id);
        } else if (item === 'story'){
         return render.RenderEditStory(id);
        } else if (item === 'question'){
          return render.RenderEditQuestion(id);
        }

    }



});
