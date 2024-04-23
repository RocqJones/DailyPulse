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
        let articlesViewModel: ArticlesViewModel
        
        init() {
            articlesViewModel = ArticlesViewModel()
            articleStateModel = articlesViewModel.articlesState.value
        }
        
        @Published var articleStateModel : ArticleStateModel
        
        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articleStateModel = articlesS
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
            
            if viewModel.articleStateModel.loading {
                Loader()
            }
            
            if let error = viewModel.articleStateModel.error {
                ErrorMessage(message: error)
            }
            
            if (!viewModel.articleStateModel.articles.isEmpty) {
                ScrollView {
                    // Lazy Vertical Stack
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articleStateModel.articles, id: \.self) { article in
                            ArticleItemView(articleModel: article)
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
    var message: String
    
    var body: some View {
        Text(message).font(.title)
    }
}

struct ArticleItemView: View {
    var articleModel: ArticleModel
    
    var body: some View {
        VStack(
            alignment: .leading,
            spacing: 8
        ) {
            AsyncImage(url: URL(string: articleModel.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!.resizable().aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error!")
                } else {
                    ProgressView()
                }
            }
            
            Text(articleModel.title).font(.title).fontWeight(.bold)
            Text(articleModel.desc).font(.body).fontWeight(.medium)
            Text(articleModel.date).frame(
                maxWidth: .infinity,
                alignment: .trailing
            ).foregroundColor(.gray).fontWeight(.light)
        }.padding(16)
    }
}
