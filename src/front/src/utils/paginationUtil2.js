
export default function (currentPage, totalPage, chapterSize = 5) {

    // first = 1, totalPage = 16, chapterSize = 5

    // currentPage = 1
    // [1,2,3,4,5]

    // currentPage = 2
    // [1,2,3,4,5]

    // currentPage = 3
    // [1,2,3,4,5]

    // currentPage = 4
    // [2,3,4,5,6]


    // currentPage가 chapterSize의 중간 값(반올림)보다 작으면

    // currentPage 1 => left -0, right +4
    // currentPage 2 => left -1, right +3

    const halfOfChapterSize = Math.ceil(chapterSize / 2);
    const halfOfLastChapter = totalPage - halfOfChapterSize;
    let extra = chapterSize - 1;

    let start;
    let end;


    if (currentPage < halfOfChapterSize) {
        start = currentPage + (1 - currentPage);
        end = currentPage + halfOfChapterSize + (halfOfChapterSize - currentPage);
    } else if (currentPage > halfOfLastChapter) {
        let remainExtra = extra - (totalPage - currentPage);
        start = currentPage + (totalPage - currentPage);
        end = currentPage - remainExtra;
    } else {
        start = currentPage - extra / 2;
        end = currentPage + (extra / 2);
        end++;
    }

    // currentPage 12
    // [10,11,12,13,14]

    // currentPage 13
    // [11,12,13,14,15]

    // currentPage 14
    // [12,13,14,15,16]

    // currentPage 15
    // [12,13,14,15,16]

    // currentPage 16
    // [12,13,14,15,16]

    // currentPage가 totalPage - halfOfChapterSize + 1 (14) 보다 크면

    // currentPage 15 => left -3, right +1
    // currentPage 16 => left -4, right +0

    const range = [];

    for (let i = start; i < end; i++) {
        range.push(i);
    }

    return {
        range,
        isPrev: currentPage > 0,
        isNext: currentPage < totalPage - 1
    }

}