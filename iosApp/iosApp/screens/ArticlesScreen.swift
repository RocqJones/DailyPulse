//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 14/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticlesScreen {
    
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        
        @Published var articleState : ArticleStateModel
        
        let articlesViewModel: ArticlesViewModel
         
        init() {
            articlesViewModel = ArticlesViewModel()
            articleState = articlesViewModel.articlesState.value as! ArticleStateModel
        }
        
        func startObserving() {
            Task {
//                for await articlesS in articlesViewModel.articlesState.collect() {
//                    self.articleState = articlesS
//                }
                
                while true {
                    // Infinite loop to continuously receive updates
                    self.articleState = articlesViewModel.articlesState.value as! ArticleStateModel
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            
            if viewModel.articleState.loading {
                Loader()
            }
            
            if let error = viewModel.articleState.error {
                ErrorMessage(message: error)
            }
            
            if (!viewModel.articleState.articles.isEmpty) {
                ScrollView {
                    // Lazy Vertical Stack
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articleState.articles, id: \.self) { articles in
                            ArticleItemView(article: articles)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles").font(.largeTitle).fontWeight(.bold)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    let message: String
    
    var body: some View {
        Text(message).font(.title)
    }
}

struct ArticleItemView: View {
    let article: ArticleModel
    
    var body: some View {
        VStack(
            alignment: .leading,
            spacing: 8
        ) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if let image = phase.image {
                    image.resizable().aspectRatio(contentMode: .fit)
                } else if let error = phase.error {
                    //print("Image loading error:", error.localizedDescription)
                    Text("Image Load Error!" + error.localizedDescription)
                } else {
                    Text("Image Could not be loaded!")
                }
            }
            
            Text(article.title).font(.title).fontWeight(.bold)
            Text(article.desc).font(.body).fontWeight(.medium)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundColor(.gray).fontWeight(.light)
        }.padding(16)
    }
}
