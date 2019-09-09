import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

interface VideoListProps: RProps {
    var videos: List<Video>
}

interface VideoListState: RState {
    var selectedVideo: Video?
}

class VideoList(props: VideoListProps) : RComponent<VideoListProps, VideoListState>(props) {
    override fun RBuilder.render() {
        for (video in props.videos) {
            p {
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        setState {
                            selectedVideo = video
                        }
                    }
                }
                if(video == state.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit): ReactElement {
    return child(VideoList::class) {
        this.attrs(handler)
    }
}