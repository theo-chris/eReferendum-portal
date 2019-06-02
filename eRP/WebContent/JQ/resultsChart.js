$(function(){

    $('#chart li').each(function(){

        var pc = $(this).attr('title');
        pc=pc >100 ?100:pc;
        $(this).children('.percent').html(pc+'%');

        var width = $(this).width();
        var length = parseInt(width,10) * parseInt(pc,10)/100;
        $(this).children('.bar').animate({'width':length + 'px'},1500);
    });

});