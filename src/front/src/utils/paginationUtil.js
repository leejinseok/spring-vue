
export default function (currentPage, totalPage, chapterSize = 5) {
    const halfOfFirstChapter = Math.ceil(chapterSize / 2);
    const halfOfLastChapter = totalPage - halfOfFirstChapter;

    let start;
    let end;

    if (currentPage < halfOfFirstChapter) {
        start = 1;
        end = chapterSize;
    } else if (currentPage > halfOfLastChapter) {
        start = totalPage - chapterSize + 1;
        end = totalPage;
    } else {
        const rest = (chapterSize - 1);
        const halfOfRest = rest / 2;
        start = currentPage - halfOfRest;
        end = currentPage + halfOfRest;
    }

    const range = [];
    for (let i = start; i <= end; i++) {
        range.push(i);
    }

    return {
        range,
        isPrev: currentPage > 1,
        isNext: currentPage < totalPage,
        first: 1,
        last: totalPage
    }
}