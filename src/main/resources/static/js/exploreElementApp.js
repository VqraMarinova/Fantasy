const [item, id] =(window.location.pathname.toString().split('/')).slice(-2);

import * as render from './RenderElement.js'


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
    loader.show();
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
        loader.hide();

    };


    targetDiv.addEventListener('click', async (e)=>{
        const target = e.target;
        if (target.tagName !== 'BUTTON'){
            return;
        }
        loader.show();
        if (target.id === 'editItem'){
           targetDiv.innerHTML = await EditHandler();
           loader.hide();
        }else if (target.id === 'editComment'){
            const commentId = target.value;
            const div = target.parentElement.previousSibling;
            const prevText = div.innerText;
            div.lastChild.remove();
            div.nextSibling.remove();
            div.innerHTML= render.RenderEditComment(commentId, prevText, item,id);
            loader.hide();
        } else if (target.id === 'cancel') {
           await renderItem();
           loader.hide();
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
