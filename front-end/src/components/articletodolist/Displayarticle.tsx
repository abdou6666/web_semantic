import React from "react";

export default function Displayarticle({ alllArticle }) {
  return alllArticle.map((article, i) => {
    return (
      <div key={i}>
        <h1>article </h1>

        <div>
          
          Ttile {article.article_title.value}
          Body {article.article_body.value}
        </div>
      </div>
    );
  });
}
